//package li.haomin.jd.util;
//
//import cn.hutool.core.lang.Pair;
//import cn.hutool.http.ContentType;
//import cn.hutool.http.HttpRequest;
//import cn.hutool.http.HttpResponse;
//import cn.hutool.json.JSONArray;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class QingLong {
//    private static final String BASE_URL = "http://127.0.0.1:5700";
//
//    public static final String JD_KEY = "JD_COOKIE";
//
//    private String auth;
//    private String username = "";
//    private String password = "";
//
//    public QingLong() {
//    }
//
//    /**
//     * 模拟登录
//     * @return
//     */
//    public boolean login() {
//        Map<String, String> params = new HashMap<>(2);
//        params.put("username", username);
//        params.put("password", password);
//        HttpResponse response = HttpRequest.post(BASE_URL + "/api/login?t=" + System.currentTimeMillis())
//                .body(JSONUtil.toJsonStr(params), ContentType.JSON.toString())
//                .execute();
//        if (response.getStatus() != 200 || !response.body().contains("code: 200")) {
//            return false;
//        }
//        JSONObject jsonObject = JSONUtil.parseObj(response.body());
//        this.auth = jsonObject.getStr("token");
//        return true;
//    }
//
//    /**
//     * 退出
//     */
//    public boolean logout() {
//        HttpResponse response = HttpRequest.post(BASE_URL + "/api/logout?t=" + System.currentTimeMillis())
//                .bearerAuth(auth)
//                .execute();
//        return response.getStatus() == 200 && response.body().contains("code: 200");
//    }
//
//    /**
//     * 环境变量
//     * @param id id
//     * @param name 名称
//     * @param value 值
//     * @param remarks 备注
//     * @return
//     */
//    public boolean envs(String id, String name, String value, String remarks) {
//        Map<String, String> params = new HashMap<>(4);
//        params.put("name", name);
//        params.put("value", value);
//        params.put("remarks", remarks);
//        final String url = BASE_URL + "/api/envs?t=" + System.currentTimeMillis();
//        HttpRequest request;
//        if (id == null) {
//            request = HttpRequest.post(url);
//        } else {
//            request = HttpRequest.put(url);
//            params.put("_id", id);
//        }
//        HttpResponse response = request.bearerAuth(auth)
//                .body(JSONUtil.toJsonStr(params), ContentType.JSON.toString())
//                .execute();
//        if (response.getStatus() != 200) {
//            return false;
//        }
//        return response.body().contains("code: 200");
//    }
//
//    /**
//     * 环境变量列表
//     */
//    public List<Pair<String, String>> envList() {
//        HttpResponse response = HttpRequest.get(BASE_URL + "/api/envs?searchValue=" + JD_KEY + "&t=" + System.currentTimeMillis())
//                .bearerAuth(auth)
//                .execute();
//        if (response.getStatus() != 200 || !response.body().contains("code: 200")) {
//            return Collections.emptyList();
//        }
//        JSONObject jsonObject = JSONUtil.parseObj(response.body());
//        JSONArray array = jsonObject.getJSONArray("data");
//        List<Pair<String, String>> list = new ArrayList<>(array.size());
//        for (int i = 0; i < array.size(); i++) {
//            JSONObject object = array.getJSONObject(i);
//            Pair<String, String> pair = Pair.of(object.getStr("_id"), object.getStr("remarks"));
//            list.add(pair);
//        }
//        return list;
//    }
//}
