package datasource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

public class DiffbotCommentProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(30000);

    @Override
    public void process(Page page) {
        JSONObject jsonObject = JSONObject.parseObject(page.getJson().toString()).getJSONArray("objects").getJSONObject(0);
        JSONArray jsonArray = jsonObject.getJSONArray("posts");
        String article_title = jsonObject.getString("title");
        String article_url = jsonObject.getString("pageUrl");
        List<String> records = new ArrayList<String>();
        for (int i = 0; i < jsonArray.size(); i++) {
            records.add(jsonArray.getJSONObject(i).get("text").toString());
        }
        new WatsonEmotionProcessor(records, article_title, article_url);
    }

    @Override
    public Site getSite() {
        return site;
    }
}