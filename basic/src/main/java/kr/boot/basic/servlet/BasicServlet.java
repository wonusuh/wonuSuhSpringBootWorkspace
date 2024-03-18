package kr.boot.basic.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name="basicServelt", urlPatterns ="/basic")
public class BasicServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(" basic servlet ");
        System.out.println("req "+req);
        System.out.println("resp "+resp);

        // 파라미터 읽어오기
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // 응답해줄때

    }
}
