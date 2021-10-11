package top.yxp2918.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * context 实现不同servlet通信
 *
 * @author yxp2918
 * @date 2021-10-11-22:43
 */
public class ServletGet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        ServletContext context = getServletContext();
        String name = (String) context.getAttribute("name");
//        获取初始化参数
        String dataSource = context.getInitParameter("dataSource");

        PrintWriter writer = resp.getWriter();
        writer.println("姓名" + name);
        writer.println("数据源" + dataSource);
//        读取properties文件
        InputStream stream = context.getResourceAsStream("/WEB-INF/classes/top/yxp2918/servlet/aa.properties");
        System.out.println(stream);
        Properties prop = new Properties();
        prop.load(stream);
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        resp.getWriter().println("username=" + username +"password=" + password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
