package datasource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentResult;
import consts.AuthInfoConstant;
import data.ResponseMap;
import model.NewsModel;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.DbProcessorUtil;
import utils.ModelJsonUtil;

import java.net.URLEncoder;
import java.util.List;

public class DiffbotArticleProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(500).setTimeOut(30000);

    @Override
    public void process(Page page) {
        JSONObject jsonObject = JSONObject.parseObject(page.getJson().toString()).getJSONArray("objects").getJSONObject(0);
        String targetUrl = JSONObject.parseObject(page.getJson().toString()).getJSONObject("request").get("pageUrl").toString();
        NewsModel model = ResponseMap.map.get(targetUrl);

        model.setText(jsonObject.get("text").toString());
        model.setTitle(jsonObject.get("title").toString());
        model.setDate(jsonObject.get("date").toString());
        model.setAuthor(jsonObject.get("author").toString());
        if (null != jsonObject.get("authorUrl"))
            model.setAuthorUrl(jsonObject.get("authorUrl").toString());

        JSONArray jsonArray = jsonObject.getJSONArray("images");
        if (null != jsonArray && jsonArray.size() > 0)
            model.setImgUrl(jsonArray.getJSONObject(0).get("url").toString());
        //Add Watson Concepts and Keywords
        List<ConceptsResult> concepts = new WastonConceptsProcessor().getConcepts(model.getText());
        List<KeywordsResult> keywords = new WatsonKeywordsProcessor().getKeywords(model.getText());
        SentimentResult sentimentResult = new WatsonSentimentProcessor().getSentiment(model.getText());
        model.setConcepts(concepts);
        model.setKeywords(keywords);
        model.setSentiment(sentimentResult.getDocument().getScore());
        model.setSentimentLabel(sentimentResult.getDocument().getLabel());
        //convert to json
        String response = ModelJsonUtil.convert(model).toJSONString();
        DbProcessorUtil.sendData(response);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String args[]) throws Exception {
        String str = "https://www.washingtonpost.com/business/2018/07/12/trump-voters-hit-hard-by-tariffs-are-standing-by-him-now/?utm_term=.3e293c22b639";
        String encodeStr = URLEncoder.encode(str, "utf-8");
        Spider.create(new DiffbotArticleProcessor()).addUrl("https://api.diffbot.com/v3/article?token=" + AuthInfoConstant.DIFFBOTTOKEN + "&url=" + encodeStr).thread(1).run();
    }
}
