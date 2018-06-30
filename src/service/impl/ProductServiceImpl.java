package service.impl;

import mapper.CustomProductMapper;
import mapper.ProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.CustomProduct;
import po.Product;
import service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CustomProductMapper customProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<CustomProduct> productList() {
        return customProductMapper.productsList();
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
    public List<CustomProduct> findProduct() {
        return customProductMapper.findProduct();
    }
}
