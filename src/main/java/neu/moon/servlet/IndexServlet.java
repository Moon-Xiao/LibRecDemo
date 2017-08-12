package neu.moon.servlet;

import neu.moon.tool.AlgorithmList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Moon on 2017/6/2.
 */
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if(type == null){
            response.sendRedirect("index?type=MEMORY-UCF");
        }else {
            //        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("test1","a");
//        hashMap.put("test2","b");
//        hashMap.put("test3","c");
//        hashMap.put("test4","d");
//        hashMap.put("test5","e");
//        request.setAttribute("conf",hashMap);

            request.setAttribute("splitList", AlgorithmList.getSplitList());
            request.setAttribute("similarityList",AlgorithmList.getSimilarityList());
            request.setAttribute("recommendList",AlgorithmList.getRecommendList(type));
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
