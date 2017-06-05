package com.eshop.command;


import com.eshop.dao.entities.Product;
import com.eshop.dao.jdbc.JDBCProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;

public class BucketCommand implements ICommand {

    private LinkedList<Product> bucket = new LinkedList<>();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productSeries = request.getParameter("addToBucket");
        String toBucket = request.getParameter("toBucket");
        if (productSeries == null) {
            return "/pages/bucket.jsp";
        } else {
            Product product = JDBCProductDAO.getInstance().findEntity(productSeries);
            bucket.add(product);
            HttpSession session = request.getSession();
            session.setAttribute("bucket", bucket);
            return "/pages/phones.jsp";
        }
    }

    public void setBucketNull(){
        this.bucket = null;
    }
}
