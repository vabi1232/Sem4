package com.food.foodres.controller.product;

import com.food.foodres.entity.Product;
import com.food.foodres.model.MySqlProductModel;
import com.food.foodres.model.ProductModel;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DetailProductServlet extends HttpServlet {
    private ProductModel productModel;
    public DetailProductServlet(){
        this.productModel = new MySqlProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productModel.findById(id);
        if (product==null){
            req.setAttribute("message","Product not found!");
            req.getRequestDispatcher("/errors/404.jsp");
        }else {
            req.setAttribute("product",product);
            req.getRequestDispatcher("/admin/products/detail.jsp").forward(req,resp);
        }
    }
}
