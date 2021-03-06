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
public class GetSplitConfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String split=request.getParameter("split");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(AlgorithmList.getSplitConf(split));
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
