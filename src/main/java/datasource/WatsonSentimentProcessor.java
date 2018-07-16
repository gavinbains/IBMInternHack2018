package datasource;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import consts.AuthInfoConstant;

public class WatsonSentimentProcessor {
    public SentimentResult getSentiment(String text){

        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                AuthInfoConstant.WATSON_VALIDDATE,
                AuthInfoConstant.WATSON_USERNAME,
                AuthInfoConstant.WATSON_PASSWD
        );

        SentimentOptions sentiment = new SentimentOptions.Builder()
                .build();

        Features features = new Features.Builder()
                .sentiment(sentiment)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        return response.getSentiment();
    }
}
