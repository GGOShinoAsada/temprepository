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
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "GetIndexPageServlet")
public class GetIndexPageServlet extends HttpServlet {
    private List<User> UserList;
    private  final static String index = "/WEB-INF/view/index.jsp";
    @Override
    public void init() throws ServletException {
        //add all users
        UserList = new CopyOnWriteArrayList<>();
        UserList.add(new User("Tom",14));
        UserList.add(new User("Tas",45));
        UserList.add(new User("ASD",65));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users",UserList);
        req.getRequestDispatcher(index).forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        if (!requestIsValid(request)) {
            doGet(request, response);
        }
        final String name = request.getParameter("name");
        final String age = request.getParameter("age");
        final User user = new User(name, Integer.valueOf(age));
        UserList.add(user);
        doGet(request, response);

    }
    private boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }
}
