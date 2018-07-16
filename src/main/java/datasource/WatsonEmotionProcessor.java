package datasource;

import com.alibaba.fastjson.JSONObject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import consts.AuthInfoConstant;
import data.ResponseMap;
import model.NewsModel;
import us.codecraft.webmagic.Spider;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WatsonEmotionProcessor {

    private int minumlength = 15;
    private Map<String, Double> totalEmotionValues = new HashMap<String, Double>();

    public WatsonEmotionProcessor(List<String> records, String article_title, String article_url) {
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                AuthInfoConstant.WATSON_VALIDDATE, //$NON-NLS-1$
                AuthInfoConstant.WATSON_USERNAME, //$NON-NLS-1$
                AuthInfoConstant.WATSON_PASSWD //$NON-NLS-1$
        );

        totalEmotionValues.put("anger", .0);
        totalEmotionValues.put("fear", .0);
        totalEmotionValues.put("disgust", .0);
        totalEmotionValues.put("sadness", .0);
        totalEmotionValues.put("joy", .0);

        List<Map<String, Double>> emotionList = new ArrayList<>();
        NewsModel model = new NewsModel();

        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).length() < minumlength)
                continue;

            SentimentOptions sentiment = new SentimentOptions.Builder()
                    .build();

            EmotionOptions emotion = new EmotionOptions.Builder()
                    .build();

            Features features = new Features.Builder()
                    .emotion(emotion)
                    .sentiment(sentiment)
                    .build();

            AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                    .text(records.get(i))
                    .features(features)
                    .build();

            AnalysisResults response = service
                    .analyze(parameters)
                    .execute();
            if (null == response)
                continue;
            Map<String, Double> emotionMap = new HashMap<>();

            EmotionScores emotionResult = response.getEmotion().getDocument().getEmotion();
            //parse
            Double angerScore = emotionResult.getAnger();
            Double fearScore = emotionResult.getFear();
            Double disgustScore = emotionResult.getDisgust();
            Double sadnessScore = emotionResult.getSadness();
            Double joyScore = emotionResult.getJoy();

            emotionMap.put("anger", angerScore);
            emotionMap.put("fear", fearScore);
            emotionMap.put("disgust", disgustScore);
            emotionMap.put("sadness", sadnessScore);
            emotionMap.put("joy", joyScore);

            emotionList.add(emotionMap);

            // SentimentResult sentiments = response.getSentiment();
        }

        model.setUrl(article_url);
        model.setTitle(article_title);

        double max_total = 0;


        for (int count = 0; count < emotionList.size(); count++) {
            for (String emotion_key : emotionList.get(count).keySet()) {
                double updated_value = totalEmotionValues.get(emotion_key) + emotionList.get(count).get(emotion_key);
                totalEmotionValues.put(emotion_key, totalEmotionValues.get(emotion_key) + emotionList.get(count).get(emotion_key));
            }
        }

        for (String emotion_key : totalEmotionValues.keySet()) {
            max_total += totalEmotionValues.get(emotion_key);
        }

        for (String emotion_key : totalEmotionValues.keySet()) {
            double overall_value = totalEmotionValues.get(emotion_key) / max_total;
            totalEmotionValues.put(emotion_key, overall_value);
            model.getEmotionMap().put(emotion_key, overall_value);
        }
        ResponseMap.map.put(article_url, model);

        System.out.println();

        try {
            Spider.create(new DiffbotArticleProcessor())
                    .addUrl("https://api.diffbot.com/v3/article?token=" + AuthInfoConstant.DIFFBOTTOKEN + "&url=" + URLEncoder.encode(model.getUrl(), "utf-8"))
                    .thread(1).run();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
