package neu.moon.tool;

import net.librec.recommender.item.RecommendedItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Moon on 2017/6/5.
 */
public class DataPack {
    public static String packData(String type,String data){
        JSONObject object = new JSONObject();
        object.put("type",type);
        object.put("data",data);
        return object.toString();
    }
    public static String packData(String type,JSONObject data){
        JSONObject object = new JSONObject();
        object.put("type",type);
        object.put("data",data);
        return object.toString();
    }
    public static String packData(String type,JSONArray data){
        JSONObject object = new JSONObject();
        object.put("type",type);
        object.put("data",data);
        return object.toString();
    }
    public static String packData(String type,int data){
        JSONObject object = new JSONObject();
        object.put("type",type);
        object.put("data",data);
        return object.toString();
    }
    public static JSONArray search(List<RecommendedItem> recommendedList, int start, int end) {
        JSONArray results = new JSONArray();
        for (int i = start; i < end&& i < recommendedList.size(); i++) {
            JSONArray items = new JSONArray();
            items.put(recommendedList.get(i).getUserId());
            items.put(recommendedList.get(i).getItemId());
            items.put(recommendedList.get(i).getValue());
            results.put(items);
        }
        return results;
    }
}
