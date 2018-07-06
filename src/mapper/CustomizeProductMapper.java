package mapper;

import po.CustomizeProduct;

import java.util.List;

public interface CustomizeProductMapper {
    List<CustomizeProduct> productsList();
    List<CustomizeProduct> findProduct(CustomizeProduct customizeProduct);
}