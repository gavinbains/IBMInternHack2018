package utils;

import com.alibaba.fastjson.JSONObject;
import consts.AuthInfoConstant;
import model.NewsModel;

public class ModelJsonUtil {
    public static JSONObject convert(NewsModel model) {
        JSONObject result = new JSONObject();
        try {
            result.put("title", model.getTitle());
            result.put("url", model.getUrl());
            //emotion
            for (String emotionKey : model.getEmotionMap().keySet()) {
                result.put(emotionKey, model.getEmotionMap().get(emotionKey));
            }
            result.put("date", model.getDate());
//        result.put("text", model.getText());
            result.put("author", model.getAuthor());
            result.put("authorUrl", model.getAuthorUrl());
            result.put("imgUrl", model.getImgUrl());
            result.put("sentiment", model.getSentiment());
            result.put("sentimentLabel", model.getSentimentLabel());
            //summary
            if (model.getText().length() < AuthInfoConstant.SUMMARY_LENGTH)
                result.put("summary", model.getText());
            else
                result.put("summary", model.getText().substring(0, AuthInfoConstant.SUMMARY_LENGTH));
            //concepts
            for (int i = 0; i < model.getConcepts().size(); i++)
                result.put("concept" + (i + 1), model.getConcepts().get(i).getText());
            //keywords
            for (int i = 0; i < model.getKeywords().size(); i++)
                result.put("key" + (i + 1), model.getKeywords().get(i).getText());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return result;
    }
}
