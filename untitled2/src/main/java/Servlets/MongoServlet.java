package main.java.Servlets;


import com.mongodb.MongoException;
import main.java.DAO.Category;
import main.java.DAO.MongoContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "MongoServlet")

public class MongoServlet extends HttpServlet {
    private List<Category> Categories;
    private MongoContext context = new MongoContext();
    private  String index = "/WEB-INF/view/index.jsp";
    @Override
    public void init() throws ServletException{
        Categories = new  CopyOnWriteArrayList<Category>();
        //добавляем с использованием драйвера MongoDb
        try{
            Categories=context.getAllCategories();
        }
        catch (MongoException ex){
            ex.printStackTrace();
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        if (isvalidrequest(request, response)){
            final String name = request.getParameter("Name");
            final String description = request.getParameter("Description");
            final double rating = Double.parseDouble(request.getParameter("Rating"));
            //additional category

            Categories.add(new Category(Categories.size(),name, description,rating));
            doGet(request,response);
        }
        else {
            doGet(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("Categories",Categories);
        request.getRequestDispatcher(index).forward(request,response);
    }
    boolean isvalidrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        boolean isvalid = true;
        final  String name = request.getParameter("Name");
        final  String description = request.getParameter("Description");
        final double rating = Double.parseDouble(request.getParameter("Rating"));
        if ((name==null) || (description==null) || (rating<0)){
            isvalid=true;
        }
        return  isvalid;
    }
}
