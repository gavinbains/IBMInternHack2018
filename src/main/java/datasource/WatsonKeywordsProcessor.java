package datasource;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import consts.AuthInfoConstant;

import java.util.List;

public class WatsonKeywordsProcessor {
    public List<KeywordsResult> getKeywords(String text) {
        //watson
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                AuthInfoConstant.WATSON_VALIDDATE,
                AuthInfoConstant.WATSON_USERNAME,
                AuthInfoConstant.WATSON_PASSWD
        );

        KeywordsOptions keywords = new KeywordsOptions.Builder()
                .sentiment(true)
                .emotion(true)
                .limit(3)
                .build();

        Features features = new Features.Builder()
                .keywords(keywords)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        return response.getKeywords();
    }
}
