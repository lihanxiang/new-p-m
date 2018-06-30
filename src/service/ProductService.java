package service;

import po.CustomProduct;
import po.Product;

import java.util.List;

public interface ProductService {

    List<CustomProduct> productList();

    Product findProductByID(String id);

    void updateProduct(Product product);

    void addProduct(Product product);

    void deleteProduct(String id);

    List<CustomProduct> findProduct();
}
