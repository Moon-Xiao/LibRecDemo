package neu.moon.recommend;

import javax.websocket.RemoteEndpoint;
//适配
public interface ModelBasedRecommenderInspector {
    void setBasic(RemoteEndpoint.Basic basic);
}
