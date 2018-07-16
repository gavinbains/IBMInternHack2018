package model;

import com.alibaba.fastjson.JSONObject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsModel {

    private String title = "";
    private String url = "";
    private HashMap<String, Double> emotionMap = new HashMap<>();

    private String text = "";
    private JSONObject response;
    private String date = "";
    private String author = "";
    private String authorUrl = "";
    private Double sentiment = .0;
    private String sentimentLabel = "positive";
    private String imgUrl = "";
    private String summary = "";

    private List<ConceptsResult> concepts = new ArrayList<>();
    private List<KeywordsResult> keywords = new ArrayList<>();

    public NewsModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Double> getEmotionMap() {
        return emotionMap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Double getSentiment() {
        return sentiment;
    }

    public void setSentiment(Double sentiment) {
        this.sentiment = sentiment;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<KeywordsResult> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<KeywordsResult> keywords) {
        this.keywords = keywords;
    }

    public void setResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return response;
    }

    public List<ConceptsResult> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<ConceptsResult> concepts) {
        this.concepts = concepts;
    }

    public String getSentimentLabel() {
        return sentimentLabel;
    }

    public void setSentimentLabel(String sentimentLabel) {
        this.sentimentLabel = sentimentLabel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
