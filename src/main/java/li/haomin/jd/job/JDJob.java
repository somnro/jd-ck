//package li.haomin.jd.job;
//
//import cn.hutool.core.date.DateUtil;
//import cn.hutool.core.lang.Pair;
//import li.haomin.jd.util.DingDing;
//import li.haomin.jd.util.QingLong;
//
//import java.util.Date;
//import java.util.List;
//
//public class JDJob {
//
//    public void run() {
//        QingLong qingLong = new QingLong();
//        qingLong.login();
//        List<Pair<String, String>> list = qingLong.envList();
//        StringBuffer sb = new StringBuffer("CK到期\n");
//        int n = 0;
//        for (Pair<String, String> pair : list) {
//            n++;
//            String[] strings = pair.getValue().split("|");
//            if (strings.length != 2) {
//                continue;
//            }
//            long ts = Long.parseLong(strings[1]);
//            if (ts < System.currentTimeMillis()) {
//                sb.append(String.format("【京东账号%d】%s 到期时间 $s \n", n, strings[0], DateUtil.format(new Date(ts), "yyyy-MM-dd")));
//            }
//        }
//        String content = sb.toString();
//        if (content.contains("京东账号")) {
//            DingDing.send(content);
//        }
//        qingLong.logout();
//    }
//}
