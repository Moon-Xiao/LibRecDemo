package neu.moon.type;

import net.librec.recommender.Recommender;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.cf.UserKNNRecommender;
import neu.moon.recommend.*;


/**
 * Created by Moon on 2017/5/31.
 */
public enum RecommenderEnum {
    UserKNNRecommender(UserKNNRecommender.class),
    ItemKNNRecommender(ItemKNNRecommender.class),
    SVDPlusPlusRecommender(SVDPlusPlusRecommenderInspector.class),
    BHFreeRecommender(BHFreeRecommenderInspector.class),
    BPRRecommender(BPRRecommenderInspector.class),
    RSTERecommender(RSTERecommenderInspector.class),
    BUCMRecommender(BUCMRecommenderInspector.class);


    private final Class<? extends Recommender> recommenderClass;
    RecommenderEnum(Class<? extends Recommender> recommenderClass){
        this.recommenderClass=recommenderClass;
    }
    public Class<? extends Recommender>getEvaluatorClass(){
        return recommenderClass;
    }
}
