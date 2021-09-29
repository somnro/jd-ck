//package li.haomin.jd.util;
//
//import cn.hutool.core.net.URLEncoder;
//import cn.hutool.crypto.digest.HMac;
//import cn.hutool.crypto.digest.HmacAlgorithm;
//import cn.hutool.http.ContentType;
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import cn.hutool.json.JSONUtil;
//
//import java.nio.charset.Charset;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DingDing {
//    private static final String BASE_URL = "https://oapi.dingtalk.com/robot/send?access_token=%s&timestamp=%d&sign=%s";
//    private static final String ACCESS_TOKEN = "";
//    private static final String SECRET = "";
//
//
//    public static boolean send(String content) {
//        long ts = System.currentTimeMillis();
//        String sign = getSign(ts);
//        Map<String, Object> body = new HashMap<>();
//        body.put("msgtype", "text");
//        body.put("text", Collections.singletonMap("content", content));
//        body.put("at", Collections.singletonMap("isAtAll", true));
//        HttpResponse response = HttpRequest.post(String.format(BASE_URL, ACCESS_TOKEN, ts, sign))
//                .body(JSONUtil.toJsonStr(body), ContentType.JSON.toString())
//                .execute();
//        return response.isOk();
//    }
//
//    private static String getSign(long ts) {
//        String key = ts + "\n" + SECRET;
//        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, SECRET.getBytes());
//        return URLEncoder.createDefault()
//                .encode(mac.digestBase64(key, false), Charset.defaultCharset());
//    }
//}
