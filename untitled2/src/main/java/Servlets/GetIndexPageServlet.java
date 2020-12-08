package main.java.Servlets;

import main.java.DAO.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "GetIndexPageServlet")
public class GetIndexPageServlet extends HttpServlet {
    private Map<Integer, User> users;
    private  final static String index = "/WEB-INF/view/index.jsp";
    @Override
    public void init() throws ServletException {

        final Object _users = getServletContext().getAttribute("users");

        users.values().addAll((List<User>)_users);

        /*if (_users == null || !(_users instanceof ConcurrentHashMap)) {

           throw new IllegalStateException("You're repo does not initialize!");
       } else {

            this.users = (ConcurrentHashMap<Integer, User>) users;
        }*/


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", users.values());
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



    }
}
