package spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import spring.model.Product;
import spring.service.IProductService;

import java.util.ArrayList;

@Component
public class ProductValidator implements Validator {
    @Autowired
    IProductService productService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ArrayList<Product> list = (ArrayList<Product>) productService.findAll();
        Product product = (Product) target;

        for (Product p:list) {
            if (p.getProductName().equals(product.getProductName())){
                errors.rejectValue("productName", "productName.duplicate", "Tên sản phẩm đã tồn tại");
                break;
            }
        }
    }
}
