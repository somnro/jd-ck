//package li.haomin.jd.action;
//
//import cn.hutool.core.lang.Pair;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.http.ContentType;
//import cn.hutool.http.server.HttpServerRequest;
//import cn.hutool.http.server.HttpServerResponse;
//import cn.hutool.http.server.action.Action;
//import li.haomin.jd.util.Login;
//import li.haomin.jd.util.QingLong;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//public class SaveAction implements Action {
//    public static final String PATH = "/save";
//
//    @Override
//    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
//        synchronized (SaveAction.class) {
//            String token = request.getParam("token");
//            if (StrUtil.isBlank(token)) {
//                response.write("token没有", ContentType.TEXT_PLAIN.toString());
//                return;
//            }
//            System.out.println("token = " + token);
//            Pair<String, Long> validation = Login.qrCodeTicketValidation(token);
//            if (validation == null) {
//                response.write("token无效", ContentType.TEXT_PLAIN.toString());
//                return;
//            }
//            QingLong qingLong = new QingLong();
////            qingLong.login();
//            List<Pair<String, String>> list = qingLong.envList();
//            Optional<Pair<String, String>> optional = list.stream()
//                    .filter(p -> p.getValue().contains(validation.getKey()))
//                    .findFirst();
//            boolean b = false;
//            String remarks = validation.getKey() + "|" + validation.getValue();
//            if (optional.isPresent()) {
//                Pair<String, String> pair = optional.get();
//                String[] strings = pair.getValue().split("|");
//                if (strings.length == 2) {
//                    long ts = Long.parseLong(strings[1]);
//                    if (ts < System.currentTimeMillis()) {
//                        b = qingLong.envs(pair.getKey(), QingLong.JD_KEY, token, remarks);
//                    }
//                } else {
//                    b = qingLong.envs(pair.getKey(), QingLong.JD_KEY, token, remarks);
//                }
//            } else {
//                b = qingLong.envs(null, QingLong.JD_KEY, token, remarks);
//            }
//            System.out.println( QingLong.JD_KEY + "\r\n" +token+ "\r\n" +remarks);
//            if (b) {
//                response.write("ok", ContentType.TEXT_PLAIN.toString());
//            } else {
//                response.write("环境变量保存失败", ContentType.TEXT_PLAIN.toString());
//            }
//            qingLong.logout();
//        }
//    }
//}
