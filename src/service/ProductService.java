package service;

import po.CustomizeProduct;
import po.Product;
import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    void deleteProduct(String id);

    void updateProduct(Product product);

    Product findProductByID(String id);

    List<CustomizeProduct> productList();

    List<CustomizeProduct> findProduct(CustomizeProduct customizeProduct);
}
