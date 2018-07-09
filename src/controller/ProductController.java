package controller;

import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import po.CustomizeProduct;
import po.Product;
import service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    //自动注入 ProductService
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //在添加商品之前，需要先得到一个带有表单的页面
    @RequestMapping("/preAdd")
    public ModelAndView preAdd(){
        return new ModelAndView("add");
    }

    //首先设置视图名称，然后设置 ID，添加商品，添加模型
    @RequestMapping("/addProduct")
    public ModelAndView addProduct(Product product){
        ModelAndView modelAndView = new ModelAndView("message");
        product.setId(CommonUtils.uuid());
        productService.addProduct(product);
        modelAndView.addObject("message","Add Product Successfully");
        return modelAndView;
    }

    @RequestMapping("/deleteProduct")
    public ModelAndView deleteProduct(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView("message");
        productService.deleteProduct(id);
        modelAndView.addObject("message","Delete Product Successfully");
        return modelAndView;
    }

    @RequestMapping("/preEdit")
    public ModelAndView editProduct(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findProductByID(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView editProductSubmit(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("message");
        productService.updateProduct(product);
        modelAndView.addObject("message","Edit Product Successfully");
        return modelAndView;
    }

    @RequestMapping("/preFind")
    public String preFind(){
        return "query";
    }

    @RequestMapping("/findProduct")
    public ModelAndView findProduct(CustomizeProduct customizeProduct){
        ModelAndView modelAndView = new ModelAndView("productsList");
        List<CustomizeProduct> productList = productService.findProduct(customizeProduct);
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @RequestMapping("/showProducts")
    public ModelAndView showProducts(){
        List<CustomizeProduct> productList = productService.productList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("productsList");
        return modelAndView;
    }

    @RequestMapping("/top")
    public String top(){
        return "top";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
