package main.java.Servlets;

import main.java.DAO.Category;
import main.java.DAO.Context;
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
    private List<Category> Categories;
    private Context context = new Context();
    private  final static String index = "/WEB-INF/view/index.jsp";
    @Override
    public void init() throws ServletException {
        //add all users
        Categories = new CopyOnWriteArrayList<>();
        try{
            Categories = context.getallcategories();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("categories",Categories);
        req.getRequestDispatcher(index).forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
       /* if (!requestIsValid(request)) {
            doGet(request, response);
        }*/

        final String name = request.getParameter("name");
        final String  description = request.getParameter("description");
        double rating = -1;
        try{
             rating = Double.parseDouble(request.getParameter("rating"));
        }
        catch (ClassCastException ex){
            System.out.println("rating "+ex.getMessage());
        }
        //check values

        final Category category = new Category(name, description, rating);

        boolean issuc = context.isconnectsuccessful();
        System.out.println("CONNECTION SUCCESSFUL: "+issuc);
        try{
           context.addCategory(category);
        }
        catch (Exception ex){
           ex.printStackTrace();
        }

        doGet(request, response);

    }
    private boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("name");
        final String age = req.getParameter("age");

        return name != null && name.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }
    protected void printvalies (Category cat){
        System.out.println("Name"+cat.getName());
        System.out.println("Description"+cat.getDescription());
        System.out.println("Rating"+cat.getRating());
    }
}
