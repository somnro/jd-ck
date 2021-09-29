package li.haomin.jd.util;

import cn.hutool.core.lang.Pair;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

import java.net.HttpCookie;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    private final static String headerAgent = "User-Agent";
    private final static String headerAgentArg = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36";
    private final static String Referer = "Referer";
    private final static String RefererArg = "https://passport.jd.com/new/login.aspx";

    /**
     * 获取二维码数据流
     */
    public static Pair<List<HttpCookie>, byte[]> show(String size) {
        HttpResponse response = HttpRequest.get("https://qr.m.jd.com/show?appid=133&size=" + size + "&t=" + System.currentTimeMillis())
                .header(headerAgent, headerAgentArg)
                .header(Referer, RefererArg)
                .execute();
        if (response.getStatus() != 200) {
            return null;
        }
        byte[] bytes = response.bodyBytes();
        List<HttpCookie> cookies = response.getCookies();
        response.close();
        return Pair.of(cookies, bytes);
    }

    /**
     * 判断是否扫二维码
     */
    public static Pair<Boolean, String> check(String cookies) {
        Pattern pattern = Pattern.compile("wlfstk_smdl=(.*?)(;|$)");
        Matcher matcher = pattern.matcher(cookies);
        if (!matcher.find()) {
            return Pair.of(false, "参数错误");
        }
        String token = matcher.group(1);
        String checkUrl = "https://qr.m.jd.com/check?appid=133&callback=jQuery"
                + (int) ((Math.random() * (9999999 - 1000000 + 1)) + 1000000)
                + "&token=" + token + "&_=" + System.currentTimeMillis();
        HttpResponse httpResponse = HttpRequest.get(checkUrl)
                .header(headerAgent, headerAgentArg)
                .header(Referer, RefererArg)
                .header("Cookie", cookies)
                .execute();
        String qrCode = httpResponse.body();
        if (qrCode.contains("二维码未扫描")) {
            return Pair.of(false, "二维码未扫描，请扫描二维码登录");
        } else if (qrCode.contains("请手机客户端确认登录")) {
            return Pair.of(false, "请手机客户端确认登录");
        } else if (qrCode.contains("二维码过期，请重新扫描")) {
            return Pair.of(false, "二维码过期，请重新扫描");
        } else if(qrCode.contains("ticket")) {
            String ticket = qrCode.split("\"ticket\" : \"")[1].split("\"\n" +
                    "}\\)")[0];
            return Pair.of(true, ticket);
        } else {
            return Pair.of(false, "二维码错误");
        }
    }

    /**
     * 验证，获取cookie
     */
    public static String qrCodeTicketValidation(String ticket) {
        HttpResponse response = HttpRequest.get("https://passport.jd.com/uc/qrCodeTicketValidation?t=" + ticket)
                .header(headerAgent, headerAgentArg)
                .header(Referer, RefererArg)
                .execute();
        if (response.getStatus() != 200) {
            return null;
        }
        HttpCookie pin = response.getCookie("pin");
        String ck = cookie(ticket, pin.getValue());
        System.out.println(ck);
        return ck;
//        return Pair.of(pin.getValue(), System.currentTimeMillis() + pin.getMaxAge() * 1000);
    }

    public static String cookie(String ticket, String pin) {
        return "pt_key=" + ticket + ";pt_pin=" + pin + ";";
    }
}
