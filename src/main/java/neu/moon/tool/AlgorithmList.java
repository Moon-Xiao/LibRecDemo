package neu.moon.tool;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Moon on 2017/6/3.
 */
public class AlgorithmList {
    public static ArrayList<String> getSplitList() {
        ArrayList<String> splitList = new ArrayList<>();
        splitList.add("ratio");
        splitList.add("loocv");
        splitList.add("given");
        splitList.add("kcv");
        return splitList;
    }

    public static ArrayList<String> getSimilarityList() {
        ArrayList<String> similarityList = new ArrayList<>();
        similarityList.add("BinaryCosineSimilarity");
        similarityList.add("CosineSimilarity");
        similarityList.add("CPCSimilarity");
        similarityList.add("MSESimilarity");
        similarityList.add("MSDSimilarity");
        similarityList.add("PCCSimilarity");
        similarityList.add("KRCCSimilarity");
        similarityList.add("DiceCoefficientSimilarity");
        similarityList.add("JaccardSimilarity");
        similarityList.add("ExJaccardSimilarity");
        return similarityList;
    }

    public static ArrayList<String> getRecommendList(String type) {
        ArrayList<String> recommendList = new ArrayList<>();
        switch (type) {
            case "MEMORY-UCF":
                recommendList.add("UserKNNRecommender");
                break;
            case "MEMORY-ICF":
                recommendList.add("ItemKNNRecommender");
                break;
            case "MODEL-MF":
                recommendList.add("BPRRecommender");
                recommendList.add("SVDPlusPlusRecommender");
                break;
            case "MODEL-PG":
                recommendList.add("BHFreeRecommender");
                recommendList.add("BUCMRecommender");
                break;
            case "MODEL-S":
                recommendList.add("RSTERecommender");
                break;
            default:
        }
        return recommendList;
    }

    /*data.splitter.*/
    public static String getSplitConf(String split) {
        JSONObject splitConf = new JSONObject();
        switch (split) {
            case "ratio":
                JSONArray objects = new JSONArray();
                objects.put("rating");
                objects.put("user");
                objects.put("userfixed");
                objects.put("item");
                objects.put("valid");
                objects.put("ratingdate");
                objects.put("userdate");
                objects.put("itemdate");
                splitConf.put("data.splitter.ratio", objects);
                splitConf.put("data.splitter.trainset.ratio", "0.8");
                break;
            case "loocv":
                JSONArray objects1 = new JSONArray();
                objects1.put("user");
                objects1.put("item");
                objects1.put("userdate");
                objects1.put("itemdate");
                splitConf.put("data.splitter.loocv", objects1);
                break;
            case "given":
                JSONArray objects2 = new JSONArray();
                objects2.put("user");
                objects2.put("item");
                objects2.put("userdate");
                objects2.put("itemdate");
                splitConf.put("data.splitter.givenn", objects2);
                splitConf.put("data.splitter.givenn.n", "10");
                break;
            case "kcv":
                splitConf.put("data.splitter.cv.index", "5");
                splitConf.put("data.splitter.cv.number", "5");
                break;
            default:
        }
        return splitConf.toString();
    }

    /*rec.recommender.*/
    public static String getSimilarityConf(String similarity, String recommend) {
        JSONObject similarityConf = new JSONObject();
        JSONArray array = new JSONArray();
        if ("MEMORY-ICF".equals(recommend)) {
            array.put("item");
            similarityConf.put("rec.recommender.similarities", array);
        } else if ("MEMORY-UCF".equals(recommend)) {
            array.put("user");
            similarityConf.put("rec.recommender.similarities", array);
        }
        return similarityConf.toString();
    }

    /*rec.recommender.*/
    public static String getRecommendConf(String recommend) {

        JSONObject recommendConf = new JSONObject();
        JSONArray bools = new JSONArray();
        bools.put("false");
        bools.put("true");
        switch (recommend) {
            case "MEMORY-ICF":
            case "MEMORY-UCF":
                recommendConf.put("rec.recommender.isranking", bools);
                recommendConf.put("rec.recommender.ranking.topn", 10);
                break;
            case "MODEL-MF":
                recommendConf.put("rec.iterator.maximum", 100);
                recommendConf.put("rec.iterator.learningrate", 0.01);
                recommendConf.put("rec.iterator.learningrate.maximum", 1000);
                recommendConf.put("rec.user.regularization", 0.01);
                recommendConf.put("rec.item.regularization", 0.01);
                recommendConf.put("rec.factor.number", 10);
                recommendConf.put("rec.learningrate.bolddriver", bools);
                recommendConf.put("rec.learningrate.decay", 1.0);
                break;
            case "MODEL-PG":
                recommendConf.put("rec.iterator.maximum", 100);
                recommendConf.put("rec.pgm.burn-in", 100);
                recommendConf.put("rec.pgm.samplelag", 100);
                break;
            case "MODEL-S":
                recommendConf.put("rec.iterator.maximum", 100);
                recommendConf.put("rec.iterator.learningrate", 0.01);
                recommendConf.put("rec.iterator.learningrate.maximum", 1000);
                recommendConf.put("rec.user.regularization", 0.01);
                recommendConf.put("rec.item.regularization", 0.01);
                recommendConf.put("rec.factor.number", 10);
                recommendConf.put("rec.learningrate.bolddriver", false);
                recommendConf.put("rec.learningrate.decay", 1.0);
                recommendConf.put("rec.social.regularization", 0.01);
            default:
        }
        return recommendConf.toString();
    }
}
