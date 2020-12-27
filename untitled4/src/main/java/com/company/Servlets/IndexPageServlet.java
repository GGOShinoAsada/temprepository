package main.java.com.company.Servlets;

import main.java.com.company.DAO.Category;
import main.java.com.company.DAO.Context;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class IndexPageServlet extends javax.servlet.http.HttpServlet {
    private List<Category> CategoryList;
    private  final static String index = "/WEB-INF/view/index.jsp";
    private Context context = new Context();
    @Override
    public void init() throws ServletException {
        try{
            CategoryList=context.getAllCategories();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        final String name = request.getParameter("name");
        final  String description = request.getParameter("description");
        double rating = -1;
        try{
            rating = Double.parseDouble(request.getParameter("rating"));
        }
        catch (ClassCastException ex){
            ex.printStackTrace();
        }
        Category c = new Category(name, description, rating);
        context.addCategory(c);
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("categories",CategoryList);
        request.getRequestDispatcher(index).forward(request,response);
    }
}
