package neu.moon.websocket;

import net.librec.common.LibrecException;
import net.librec.recommender.item.RecommendedItem;
import neu.moon.algorithm.CF;
import neu.moon.tool.DataPack;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/recommend")
public class RecommendSocket {
    private final static int PAGE = 10;
    private CF cf;
    private ArrayList<String> userList;
    private ArrayList<String> itemList;

    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException, InterruptedException, LibrecException, InstantiationException, IllegalAccessException, EncodeException {
        JSONObject jsonObject = new JSONObject(message);
        String type = jsonObject.getString("type");
        JSONObject data = jsonObject.getJSONObject("data");
        System.out.println(data.toString());
        if (cf == null && !type.equals("recommend")) {
            //error
            session.getBasicRemote().sendText(DataPack.packData("error", "Please run the recommend algorithm first!"));
        } else {
            switch (type) {
                case "recommend"://input recommend
                    JSONObject conf = (JSONObject) data.get("conf");
                    cf = new CF(conf.toMap());
                    String AlgoType = data.getJSONObject("recommend").getString("type");
                    if (AlgoType.equals("MODEL")) {
                        cf.run(data.getJSONObject("recommend").getString("name"), session.getBasicRemote());//used for model_CF
                    } else if (AlgoType.equals("MEMORY")) {
                        cf.run(data.getString("similarity"), data.getJSONObject("recommend").getString("name"));
                    }
                    List<RecommendedItem> recommendedList = cf.getRecommendedList();
                    int pageSize = (int) Math.ceil((float) recommendedList.size() / PAGE);
                    session.getBasicRemote().sendText(DataPack.packData("page_size", pageSize));
                    Map<String, String> evaluate = cf.evaluate();
                    JSONObject evaluateJson = new JSONObject();
                    for (String key : evaluate.keySet()) {
                        evaluateJson.put(key, evaluate.get(key));
                    }
                    session.getBasicRemote().sendText(DataPack.packData("evaluate", evaluateJson));
                    session.getBasicRemote().sendText(DataPack.packData("items", DataPack.search(recommendedList, 0 * PAGE, 1 * PAGE)));
                    break;
                case "get_info"://change page
                    int pageNum = data.getInt("pageNum");
                    //返回数据
                    List<RecommendedItem> recommendList;
                    if (userList.isEmpty() && itemList.isEmpty()) {
                        recommendList = cf.getRecommendedList();
                    } else {
                        recommendList = cf.filter(userList, itemList);
                    }
                    session.getBasicRemote().sendText(DataPack.packData("items", DataPack.search(recommendList, (pageNum - 1) * PAGE, pageNum * PAGE)));
                    break;
                case "filter"://input search
                    JSONArray user_list = data.getJSONArray("user_list");
                    JSONArray item_list = data.getJSONArray("item_list");
                    List<RecommendedItem> filterRecommendList;
                    userList.clear();
                    itemList.clear();
                    for (Object o : user_list) {
                        userList.add(o.toString());
                    }
                    for (Object o : item_list) {
                        itemList.add(o.toString());
                    }
                    if (userList.isEmpty() && itemList.isEmpty()) {
                        filterRecommendList = cf.getRecommendedList();
                    } else {
                        filterRecommendList = cf.filter(userList, itemList);
                    }
                    int filterPageSize = (int) Math.ceil((float) filterRecommendList.size() / PAGE);
                    session.getBasicRemote().sendText(DataPack.packData("page_size", filterPageSize));
                    session.getBasicRemote().sendText(DataPack.packData("items", DataPack.search(filterRecommendList, 0 * PAGE, 1 * PAGE)));
                    break;
                default:
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        userList = new ArrayList<>();
        itemList = new ArrayList<>();
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {

    }


}
