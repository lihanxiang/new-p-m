package mapper;

import org.springframework.stereotype.Component;
import po.CustomProduct;
import po.Product;

import java.util.List;

public interface CustomProductMapper {
    List<CustomProduct> productsList();

    List<CustomProduct> findProduct();
}
