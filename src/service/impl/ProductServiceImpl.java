package service.impl;

import mapper.CustomizeProductMapper;
import mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.CustomizeProduct;
import po.Product;
import service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final CustomizeProductMapper customizeProductMapper;

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(CustomizeProductMapper customizeProductMapper, ProductMapper productMapper) {
        this.customizeProductMapper = customizeProductMapper;
        this.productMapper = productMapper;
    }

    @Override
    public List<CustomizeProduct> productList() {
        return customizeProductMapper.productsList();
    }

    @Override
    public Product findProductByID(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public void addProduct(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void deleteProduct(String id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<CustomizeProduct> findProduct(CustomizeProduct customizeProduct) {
        return customizeProductMapper.findProduct(customizeProduct);
    }
}
