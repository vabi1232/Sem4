package com.food.foodres.controller.product;

import com.food.foodres.entity.Product;
import com.food.foodres.model.MySqlProductModel;
import com.food.foodres.model.ProductModel;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {
    private ProductModel productModel;
    public DeleteProductServlet(){
        this.productModel = new MySqlProductModel();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productModel.findById(id);
        if (product == null) {
            req.setAttribute("message", "Student not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            boolean result = productModel.delete(id); // xoá mềm.
            if (result) {
                resp.sendRedirect("/admin/products/list");
            } else {
                req.setAttribute("message", "Action fails!");
                req.getRequestDispatcher("/admin/errors/500.jsp").forward(req, resp);
            }
        }
    }

}
