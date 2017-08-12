package neu.moon.servlet;

import neu.moon.tool.AlgorithmList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Moon on 2017/6/3.
 */
public class GetSimilarityConfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String similarity=request.getParameter("similarity");
        String recommend=request.getParameter("recommend");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(AlgorithmList.getSimilarityConf(similarity,recommend));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
