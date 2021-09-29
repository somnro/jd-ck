package li.haomin.jd.action;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;
import li.haomin.jd.util.Login;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAction implements Action {
    public static final String PATH = "/show";

    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        String size = request.getParam("size");
        size = StrUtil.isBlank(size) ? "320" : size;
        Pair<List<HttpCookie>, byte[]> show = Login.show(size);
        List<String> list = show.getKey().stream().map(p -> String.format("%s=%s", p.getName(), p.getValue()))
                .collect(Collectors.toList());
        response.setHeader("Set-Cookie", list);
        response.write(show.getValue(), "image/png");
    }
}
