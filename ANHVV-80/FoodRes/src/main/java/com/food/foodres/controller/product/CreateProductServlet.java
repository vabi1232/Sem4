package com.food.foodres.controller.product;

import com.food.foodres.entity.Product;
import com.food.foodres.entity.myenum.ProductStatus;
import com.food.foodres.model.CategoryModel;
import com.food.foodres.model.MySqlCategoryModel;
import com.food.foodres.model.MySqlProductModel;
import com.food.foodres.model.ProductModel;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CreateProductServlet extends HttpServlet {

    private ProductModel productModel;
    private CategoryModel categoryModel;

    public CreateProductServlet() {
        this.productModel = new MySqlProductModel();
        this.categoryModel = new MySqlCategoryModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("categories", categoryModel.findAll());
        req.setAttribute("obj", new Product());
        req.setAttribute("action", 1);
        req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        int status = Integer.parseInt(req.getParameter("status"));
        String description = req.getParameter("description");
        String detail = req.getParameter("detail");
        String thumbnail = req.getParameter("thumbnail");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product();
        product.setName(name);
        product.setCategoryId(categoryId);
        product.setStatus(ProductStatus.of(status));
        product.setThumbnail(thumbnail);
        product.setDescription(description);
        product.setDetail(detail);
        product.setPrice(price);

        if (!product.isValid()) {
            req.setAttribute("categories", categoryModel.findAll());
            req.setAttribute("obj", product);
            req.setAttribute("action", 1);
            req.setAttribute("errors", product.getErrors());
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
            return;
        }
        if (productModel.save(product) != null) {
            resp.sendRedirect("/admin/products/list");
        } else {
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req, resp);
        }
    }
}
