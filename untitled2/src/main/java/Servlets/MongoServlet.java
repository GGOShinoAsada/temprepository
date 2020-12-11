package main.java.Servlets;


import main.java.DAO.Category;

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
    private  String index = "/WEB-INF/view/index.jsp";
    @Override
    public void init() throws ServletException{
        Categories = new  CopyOnWriteArrayList<Category>();
        Categories.addAll(Arrays.asList(
                new Category(1,"Tim","This is a man",9.5),
                new Category(2,"Tom","This is a man",8.4),
                new Category(3,"Jimmi","this is a man",7.9),
                new Category(4,"Jonny","This is a best man",8.5)
        ));

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
