package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.model.Category;
import spring.model.Product;
import spring.service.ICategoryService;
import spring.service.IProductService;
import spring.validator.ProductValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Value("${uploadPath}")
    private String uploadPath;
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    ProductValidator productValidator;

    @ModelAttribute("categorys")
    public List<Category> getCategorys() {
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping("/manage-product")
    public ModelAndView showManageProduct() {
        ModelAndView modelAndView = new ModelAndView("/showproduct");
        modelAndView.addObject("products", (ArrayList<Product>)productService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm() {
        return new ModelAndView("/createproduct", "product", new Product());
    }

    @PostMapping("/create-product")
    public ModelAndView createProduct(@RequestParam("imgProduct") MultipartFile imgProduct, @RequestParam("category") Long id , @Validated @ModelAttribute Product product, BindingResult bindingResult) {
        productValidator.validate(product, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/createproduct", "product", product);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/manage-product");
        try {
            String imgName = imgProduct.getOriginalFilename();
            FileCopyUtils.copy(imgProduct.getBytes(), new File(uploadPath + imgName));
            product.setImg("/" + imgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.save(product);
        return modelAndView;
    }

    @GetMapping("/delete-product")
    public ModelAndView deleteProduct(@RequestParam("id") Long id) {
        productService.deleteById(id);
        return new ModelAndView("redirect:/admin/manage-product");
    }

    @GetMapping("/edit-product")
    public ModelAndView showEditForm(@RequestParam("id") Long id) {
        return new ModelAndView("editproduct","product", productService.findById(id).get());
    }

    @PostMapping("/edit-product")
    public ModelAndView editProduct(@RequestParam("id") Long id, @RequestParam("imgProduct") MultipartFile imgProduct, @ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/manage-product");
        try {
            String imgName = imgProduct.getOriginalFilename();
            FileCopyUtils.copy(imgProduct.getBytes(), new File(uploadPath + imgName));
            product.setImg("/" + imgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.save(product);
        return modelAndView;
    }


}
