package com.eshop.command;

import com.eshop.dao.entities.Order;
import com.eshop.dao.entities.OrderEntry;
import com.eshop.dao.entities.Product;
import com.eshop.dao.entities.User;
import com.eshop.dao.jdbc.JDBCProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Order cart = (Order) session.getAttribute("cart");
        String productSeries = request.getParameter("addToBucket");
        String cancelOrder = request.getParameter("cancelOrder");
        if (productSeries == null) {
            if(cancelOrder != null){
                cart = null;
                session.setAttribute("cart", cart);
            }
            return "/pages/bucket.jsp";

        } else {
            Product product = JDBCProductDAO.getInstance().findEntity(productSeries);

            @SuppressWarnings("unchecked")

            User user = (User) session.getAttribute("user");
            if (cart == null) {
                cart = new Order();
                OrderEntry entry = new OrderEntry();
                entry.setPrice(product.getPrice());
                entry.setProduct(product);
                entry.setQuantity(1);
                List<OrderEntry> entries = new ArrayList<>();
                entries.add(entry);
                cart.setUser(user);
                cart.setTotalPrice(product.getPrice());
                cart.setEntries(entries);
                session.setAttribute("cart", cart);
            } else {
                List<OrderEntry> entries = cart.getEntries();
                Optional<OrderEntry> any = entries.stream().filter(i -> i.getProduct().getId().equals(product.getId())).findAny();
                if (any.isPresent()) {
                    OrderEntry entry = any.get();
                    entry.setQuantity(entry.getQuantity() + 1);
                    entry.setPrice(entry.getPrice() * entry.getQuantity());
                } else {
                    OrderEntry entry = new OrderEntry();
                    entry.setPrice(product.getPrice());
                    entry.setProduct(product);
                    entry.setQuantity(1);
                    entries.add(entry);
                }
                cart.setTotalPrice(cart.getTotalPrice() + product.getPrice());
            }
            return "/pages/phones.jsp";
        }
    }
}
