package neu.moon.type;

import net.librec.similarity.RecommenderSimilarity;

public enum SimilarityEnum {
    BinaryCosineSimilarity(net.librec.similarity.BinaryCosineSimilarity.class),
    JaccardSimilarity( net.librec.similarity.JaccardSimilarity.class),
    PCCSimilarity( net.librec.similarity.PCCSimilarity.class),
    CosineSimilarity( net.librec.similarity.CosineSimilarity.class),
    CPCSimilarity( net.librec.similarity.CPCSimilarity.class),
    MSESimilarity( net.librec.similarity.MSESimilarity.class),
    MSDSimilarity( net.librec.similarity.MSDSimilarity.class),
    KRCCSimilarity( net.librec.similarity.KRCCSimilarity.class),
    DiceCoefficientSimilarity( net.librec.similarity.DiceCoefficientSimilarity.class),
    ExJaccardSimilarity( net.librec.similarity.ExJaccardSimilarity.class);

    private final Class<? extends RecommenderSimilarity> similarityClass;

    SimilarityEnum(Class<? extends RecommenderSimilarity> similarityClass) {
        this.similarityClass = similarityClass;
    }

    public Class<? extends RecommenderSimilarity> getSimilarityClass() {
        return similarityClass;
    }

//    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        Class<? extends RecommenderSimilarity> pccSimilarity = SimilarityEnum.valueOf("PCCSimilarity").getSimilarityClass();
//        RecommenderSimilarity recommenderSimilarity = pccSimilarity.newInstance();
//    }
}
