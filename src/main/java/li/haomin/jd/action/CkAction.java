package li.haomin.jd.action;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import li.haomin.jd.util.Login;

import java.io.IOException;

public class CkAction implements Action {
    public static final String PATH = "/ck";


    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        synchronized (CkAction.class) {
            String token = request.getParam("token");
            if (StrUtil.isBlank(token)) {
                response.write("token没有", ContentType.TEXT_PLAIN.toString());
                return;
            }
            System.out.println("token = " + token);
            String ck = Login.qrCodeTicketValidation(token);
            JSONObject opt = new JSONObject().putOpt("ck", ck);
            response.write(opt.toString(), ContentType.JSON.toString());
        }
    }
}
