package utils;

import datasource.SendDataProcessor;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

public class DbProcessorUtil {
    public static void sendData(String data) {
        String url = "https://service.us.apiconnect.ibmcloud.com/gws/apigateway/api/" + AuthInfoConstant.CLOUD_KEY + "/what_the_hack/entries";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.PUT);
        request.addHeader("Content-Type", "application/json");
        request.setRequestBody(HttpRequestBody.json(data, "utf-8"));
        Spider spider = new Spider(new SendDataProcessor());
        spider.addRequest(request).thread(1).run();
    }
}
