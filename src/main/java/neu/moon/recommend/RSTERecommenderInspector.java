package neu.moon.recommend;


import net.librec.common.LibrecException;
import net.librec.recommender.context.rating.RSTERecommender;
import neu.moon.tool.DataPack;
import org.json.JSONArray;

import javax.websocket.RemoteEndpoint;
import java.io.IOException;

public class RSTERecommenderInspector extends RSTERecommender implements ModelBasedRecommenderInspector{
    private RemoteEndpoint.Basic basic;

    public void setBasic(RemoteEndpoint.Basic basic) {
        this.basic = basic;
    }

    @Override
    protected boolean isConverged(int iter){
        float delta_loss = (float) (lastLoss - loss);

        // print out debug info
        if (verbose) {
            String recName = getClass().getSimpleName();
            String info = recName + " iter " + iter + ": loss = " + loss + ", delta_loss = " + delta_loss;
            try {
                JSONArray objects = new JSONArray();
                objects.put(iter);
                objects.put(loss);
                basic.sendText(DataPack.packData("model",objects));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Double.isNaN(loss) || Double.isInfinite(loss)) {
            LOG.error("Loss = NaN or Infinity: current settings does not fit the recommender! Change the settings and try again!");
            try {
                throw new LibrecException("Loss = NaN or Infinity: current settings does not fit the recommender! Change the settings and try again!");
            } catch (LibrecException e) {
                e.printStackTrace();
            }
        }

        // check if converged

        return Math.abs(loss) < 1e-5;
    }
}
