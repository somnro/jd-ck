package li.haomin.jd;

import cn.hutool.cron.CronUtil;
import cn.hutool.http.HttpUtil;
import li.haomin.jd.action.CheckAction;
import li.haomin.jd.action.CkAction;
import li.haomin.jd.action.PageAction;
import li.haomin.jd.action.ShowAction;

public class Main {
    public static void main(String[] args) {
//        DingDing.send("CK检查通知\n 【京东账号1】 test 过期时间 2021-01-01\n");
//        CronUtil.start();
        if (args.length == 1){

        }else {
            args = new String[]{"23323"};
        }
        System.out.println("start... port: " + args[0]);
        HttpUtil.createServer(Integer.valueOf(args[0]))
                .addAction(PageAction.PATH, new PageAction())
                .addAction(ShowAction.PATH, new ShowAction())
                .addAction(CheckAction.PATH, new CheckAction())
//                .addAction(SaveAction.PATH, new SaveAction())
                .addAction(CkAction.PATH, new CkAction())
                .start();
    }
}
