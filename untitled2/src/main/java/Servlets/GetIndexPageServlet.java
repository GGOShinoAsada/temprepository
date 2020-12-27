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
            Categories = context.getAllCategories();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("Categories",Categories);
        req.getRequestDispatcher(index).forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        if (!requestIsValid(request)){
            doGet(request,response);
        }
        else {
            final String name = request.getParameter("Name");
            final String  description = request.getParameter("Description");
            double rating = -1;
            try{
                rating = Double.parseDouble(request.getParameter("Rating"));
            }
            catch (ClassCastException ex){
                System.out.println(ex.getMessage());
            }



            final Category category = new Category(name, description, rating);

            boolean issuc = context.isconnectionSuccessfull();

            System.out.println("CONNECTION SUCCESSFUL: "+issuc);
            try{
                context.addCategory(category);
                Categories.add(category);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            doGet(request, response);
        }



    }
    private boolean requestIsValid(final HttpServletRequest req) {

        final String name = req.getParameter("Name");
        final String description = req.getParameter("Description");
        final  double rating = Double.parseDouble(req.getParameter("Rating"));

        return name != null && name.length() > 0 &&
                description != null && description.length() > 0 &&
                rating>0;
    }
    protected void printvalies (Category cat){
        System.out.println("Name"+cat.getName());
        System.out.println("Description"+cat.getDescription());
        System.out.println("Rating"+cat.getRating());
    }
}
