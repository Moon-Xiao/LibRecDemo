package neu.moon.type;

import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.ranking.*;
import net.librec.eval.rating.*;

/**
 * Created by Moon on 2017/5/31.
 */
public enum EvaluatorEnum {

    AUCEvaluator(AUCEvaluator.class),
    AveragePrecisionEvaluator(AveragePrecisionEvaluator.class),
    AverageReciprocalHitRankEvaluator(AverageReciprocalHitRankEvaluator.class),
    DiversityEvaluator(DiversityEvaluator.class),
    HitRateEvaluator(HitRateEvaluator.class),
    IdealDCGEvaluator(IdealDCGEvaluator.class),
    NormalizedDCGEvaluator(NormalizedDCGEvaluator.class),
    PrecisionEvaluator(PrecisionEvaluator.class),
    RecallEvaluator(RecallEvaluator.class),
    ReciprocalRankEvaluator(ReciprocalRankEvaluator.class),
    MAEEvaluator(MAEEvaluator.class),
    MPEEvaluator(MPEEvaluator.class),
    MSEEvaluator(MSEEvaluator.class),
    RMSEEvaluator(RMSEEvaluator.class);

    private final Class<? extends RecommenderEvaluator> evaluatorClass;
    EvaluatorEnum(Class<? extends RecommenderEvaluator> evaluatorClass){
        this.evaluatorClass=evaluatorClass;
    }
    public Class<? extends RecommenderEvaluator>getEvaluatorClass(){
        return evaluatorClass;
    }
}
