package service;

import consts.AuthInfoConstant;
import datasource.DiffbotCommentProcessor;
import us.codecraft.webmagic.Spider;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Startup {
    public static void main(String args[]) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("https://www.washingtonpost.com/technology/2018/07/12/fcc-just-changed-its-rules-handling-consumer-complaints/?utm_term=.dc9f8153d2b3");
//        list.add("https://www.washingtonpost.com/business/economy/trump-administration-takes-major-step-to-help-chinese-firm-zte/2018/07/11/e898cd2e-852c-11e8-9e80-403a221946a7_story.html?noredirect=on&utm_term=.e4df45dbe3b6");
        list.add("https://www.washingtonpost.com/ellipsis/airbnb-benefits-local-economies-but-only-in-white-neighborhoods-study-finds/2018/07/11/d57febae-c1e1-49e1-b025-e0180589f1ec_story.html");
        list.add("https://www.washingtonpost.com/news/business/wp/2018/07/10/pfizer-buckles-under-pressure-from-trump-delays-drug-price-increases/");
        list.add("https://www.washingtonpost.com/news/business/wp/2018/07/09/a-study-of-uber-drivers-found-that-workplace-flexibility-may-not-close-the-gender-pay-gap/");
        list.add("https://www.washingtonpost.com/news/early-lead/wp/2018/07/09/kobe-bryant-ending-retirement-to-play-with-lebron-james-would-be-dope-says-matt-barnes/");
//        list.add("https://www.washingtonpost.com/news/sports/wp/2018/07/09/how-exactly-will-the-kawhi-leonard-situation-end-and-other-lingering-nba-offseason-questions/");
//        list.add("https://www.washingtonpost.com/goingoutguide/music/bacchaes-punk-rock-anti-style-starts-in-the-streets/2018/06/26/a1f6725c-7403-11e8-b4b7-308400242c2e_story.html");
        list.add("https://www.washingtonpost.com/goingoutguide/music/4-concerts-to-catch-in-the-washington-area-over-the-next-several-days/2018/06/12/28105680-69ac-11e8-bf8c-f9ed2e672adf_story.html");
//        list.add("https://www.washingtonpost.com/news/reliable-source/wp/2018/07/11/dwayne-the-rock-johnson-is-too-busy-making-heaps-of-cash-to-run-for-president-in-2020/");
//        list.add("https://www.washingtonpost.com/world/asia_pacific/5-things-to-know-about-teslas-china-plans/2018/07/12/56359352-85bf-11e8-9e06-4db52ac42e05_story.html");
//        list.add("https://www.washingtonpost.com/world/asia_pacific/afghan-officials-taliban-kill-at-least-15-troops-4-police/2018/07/12/82cd93be-85c0-11e8-9e06-4db52ac42e05_story.html");
        list.add("https://www.washingtonpost.com/world/europe/juncker-struggles-before-gala-leaders-step-in-to-help/2018/07/12/055c7632-85d1-11e8-9e06-4db52ac42e05_story.html");
        list.add("https://www.washingtonpost.com/news/speaking-of-science/wp/2018/07/11/every-supreme-court-justice-attended-harvard-or-yale-thats-a-problem-say-decision-making-experts/");
        list.add("https://www.washingtonpost.com/news/animalia/wp/2018/07/10/how-animals-on-social-media-are-redefining-cute/");
//        list.add("https://www.washingtonpost.com/national/health-science/that-aching-back-is-hard-to-fix-but-here-are-a-few-things-to-try/2018/07/06/4e719048-64e1-11e8-99d2-0d678ec08c2f_story.html");
        list.add("https://www.washingtonpost.com/news/speaking-of-science/wp/2018/07/04/beautiful-embryos-created-from-near-extinct-rhinoceros-sperm/");
        list.add("https://www.washingtonpost.com/news/speaking-of-science/wp/2018/07/03/scientists-respond-to-court-ruling-on-travel-ban-with-fear-and-frustration/");
        list.add("https://www.washingtonpost.com/business/2018/07/12/trump-voters-hit-hard-by-tariffs-are-standing-by-him-now/?utm_term=.3e293c22b639");
        list.add("https://www.washingtonpost.com/politics/most-americans-oppose-key-elements-of-trump-immigration-policy/2018/07/05/36124360-7e3d-11e8-b0ef-fffcabeff946_story.html");
        list.add("https://www.washingtonpost.com/local/md-politics/six-takeaways-from-the-latest-polls-in-the-maryland-governors-race/2018/06/10/f063b028-6d03-11e8-bd50-b80389a4e569_story.html");
        list.add("https://www.washingtonpost.com/politics/in-hindsight-americans-are-more-critical-of-demonstrators-today-than-protesters-50-years-ago/2018/05/23/d5661270-578c-11e8-b656-a5f8c2a9295d_story.html");
        list.add("https://www.washingtonpost.com/news/the-fix/wp/2018/04/17/post-abc-poll-public-supports-trump-kim-meeting-but-isnt-getting-its-hopes-up/");
//        list.add("https://www.washingtonpost.com/news/acts-of-faith/wp/2017/12/14/is-it-possible-that-white-evangelicals-swung-the-alabama-election-against-roy-moore/");
        list.add("https://www.washingtonpost.com/news/the-fix/wp/2018/07/12/trump-is-battling-mueller-investigation-to-a-draw-in-court-of-public-opinion/");
        list.add("https://www.washingtonpost.com/powerpost/conservatives-launch-5-million-effort-to-pressure-democrats-on-brett-kavanaugh/2018/07/10/97e61c12-8476-11e8-8f6c-46cb43e3f306_story.html");
//        list.add("https://www.washingtonpost.com/powerpost/democrats-ready-abolish-ice-legislation/2018/07/10/37b420d0-8478-11e8-8553-a3ce89036c78_story.html");
        list.add("https://www.washingtonpost.com/powerpost/in-arizona-a-former-trump-critic-moves-right-on-immigration-is-she-too-far-right/2018/07/07/4ddfa8b0-7e43-11e8-b660-4d0f9f0351f1_story.html");
//        list.add("https://www.washingtonpost.com/powerpost/republicans-on-russia-trip-face-scorn-and-ridicule-from-critics-at-home/2018/07/05/68f0f810-807e-11e8-b0ef-fffcabeff946_story.html");
//        list.add("https://www.washingtonpost.com/powerpost/sen-jon-tester-takes-out-newspaper-ads-welcoming-trump-to-montana-ahead-of-a-hostile-visit/2018/07/05/d894726a-8064-11e8-b0ef-fffcabeff946_story.html");
//        list.add("https://www.washingtonpost.com/powerpost/house-judiciary-panel-subpoenas-fbi-official-who-sent-anti-trump-texts/2018/07/03/49c34062-7eec-11e8-bb6b-c1cb691f1402_story.html");
        list.add("https://www.washingtonpost.com/news/monkey-cage/wp/2018/07/11/women-won-big-in-mexicos-elections-taking-nearly-half-the-legislatures-seats-heres-why/");
        list.add("https://www.washingtonpost.com/news/monkey-cage/wp/2018/07/10/middle-powers-could-emerge-as-the-heroes-of-the-liberal-world-order-and-nato/");
        list.add("https://www.washingtonpost.com/news/monkey-cage/wp/2018/07/06/china-africa-military-ties-have-deepened-here-are-4-things-to-know/");
        list.add("https://www.washingtonpost.com/news/monkey-cage/wp/2018/07/06/whats-next-for-mexico-here-are-5-things-you-need-to-know/");
        String encodeStr = URLEncoder.encode(list.get(34), "utf-8"); //1,6,7,9,10,11, 15, 23, 26, 28, 29 30date
        Spider.create(new DiffbotCommentProcessor()).addUrl("https://api.diffbot.com/v3/discussion?token=" + AuthInfoConstant.DIFFBOTTOKEN + "&url=" + encodeStr).thread(1).run();
    }
}
