package li.haomin.jd.action;

import cn.hutool.http.ContentType;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;

import java.io.IOException;
import java.io.InputStream;

public class PageAction implements Action {
    public static final String PATH = "/";

    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/index.html");
        response.write(inputStream, ContentType.TEXT_HTML.toString());
    }
}
