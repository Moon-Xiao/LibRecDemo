package neu.moon.algorithm;

import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.RecommenderSimilarity;
import neu.moon.recommend.ModelBasedRecommenderInspector;
import neu.moon.type.EvaluatorEnum;
import neu.moon.type.RecommenderEnum;
import neu.moon.type.SimilarityEnum;

import javax.websocket.RemoteEndpoint;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2017/5/31.
 */
public class CF {
    private Configuration conf;//配置
    private RecommenderContext context;//上下文
    private TextDataModel dataModel;
    private Recommender recommender;
    private RemoteEndpoint.Basic basic;

    public CF() {
        conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("librec.properties");
        conf.addResource(resource);
    }

    public CF(Map<String, Object> maps) {
        conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("librec.properties");
        conf.addResource(resource);
        addConf(maps);
    }

    private void buildData() throws LibrecException {
        dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();
        context = new RecommenderContext(conf, dataModel);
    }

    private void buildSimilarity(RecommenderSimilarity similarity) {
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);
    }

    private void recommend(Recommender recommender) throws LibrecException {
        this.recommender = recommender;
        if (recommender instanceof ModelBasedRecommenderInspector) {
            ((ModelBasedRecommenderInspector) recommender).setBasic(basic);
        }
        this.recommender.setContext(context);
        // run recommender algorithm
        this.recommender.recommend(context);
    }

    private double evaluate(RecommenderEvaluator evaluator) throws LibrecException {
        try {
            return recommender.evaluate(evaluator);
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    public void addConf(Map<String, Object> maps) {
        for (String key : maps.keySet()) {
            conf.set(key, maps.get(key).toString());
        }
    }

    public List<RecommendedItem> getRecommendedList() {
        return recommender.getRecommendedList();
    }

    public List<RecommendedItem> filter(List<String> userIdList, List<String> itemIdList) {
        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
        GenericRecommendedFilter filter = new GenericRecommendedFilter();
        filter.setUserIdList(userIdList);
        filter.setItemIdList(itemIdList);
        recommendedItemList = filter.filter(recommendedItemList);
        return recommendedItemList;
    }

    public Map<String, String> evaluate() throws IllegalAccessException, InstantiationException, LibrecException {
        String canonicalName = recommender.getClass().getCanonicalName();
        String[] evaluators = null;
        if (canonicalName.contains("raking") || conf.get("rec.recommender.isranking", "none").equals("true")) {
            evaluators = new String[]{"AUCEvaluator", "AveragePrecisionEvaluator", "AverageReciprocalHitRankEvaluator", "DiversityEvaluator",
                    "HitRateEvaluator", "IdealDCGEvaluator", "NormalizedDCGEvaluator", "PrecisionEvaluator", "RecallEvaluator", "ReciprocalRankEvaluator"};
        } else if (canonicalName.contains("rating")) {
            evaluators = new String[]{"MAEEvaluator", "MPEEvaluator", "MSEEvaluator", "RMSEEvaluator"};
        } else {
            return new HashMap<>();
        }
        HashMap<String, String> evaluatorMap = new HashMap<>();
        DecimalFormat df= new DecimalFormat("######0.00");//取float前两位输出
        for (String evaluator : evaluators) {
            double evaluate = evaluate(EvaluatorEnum.valueOf(evaluator).getEvaluatorClass().newInstance());
            if (!Double.isNaN(evaluate) && evaluate > 0)
                evaluatorMap.put(evaluator, df.format(evaluate));
        }
        return evaluatorMap;
    }

    //Memory
    public void run(String similarity, String recommend) throws IllegalAccessException, InstantiationException, LibrecException {
        buildData();
        buildSimilarity(SimilarityEnum.valueOf(similarity).getSimilarityClass().newInstance());
        recommend(RecommenderEnum.valueOf(recommend).getEvaluatorClass().newInstance());
    }

    //Model
    public void run(String recommend, RemoteEndpoint.Basic basic) throws IllegalAccessException, InstantiationException, LibrecException {
        this.basic = basic;
        buildData();
        recommend(RecommenderEnum.valueOf(recommend).getEvaluatorClass().newInstance());
    }
}
