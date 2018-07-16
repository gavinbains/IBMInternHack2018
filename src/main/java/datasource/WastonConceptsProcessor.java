package datasource;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;
import consts.AuthInfoConstant;

import java.util.List;

public class WastonConceptsProcessor {
    public List<ConceptsResult> getConcepts(String text) {
        //watson
        NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(
                AuthInfoConstant.WATSON_VALIDDATE,
                AuthInfoConstant.WATSON_USERNAME,
                AuthInfoConstant.WATSON_PASSWD
        );

        ConceptsOptions concepts= new ConceptsOptions.Builder()
                .limit(3)
                .build();

        Features features = new Features.Builder()
                .concepts(concepts)
                .build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                .text(text)
                .features(features)
                .build();

        AnalysisResults response = service
                .analyze(parameters)
                .execute();
        return response.getConcepts();
    }
}
