package controller;

import cn.itcast.commons.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import po.CustomProduct;
import po.Product;
import service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("preFind")
    public String preFind(){
        return "query";
    }

    @RequestMapping("/findProduct")
    public ModelAndView findProduct(){
        List<CustomProduct> productList = productService.findProduct();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getName());
        }
        ModelAndView modelAndView = new ModelAndView("queryList");
        modelAndView.addObject("productsList", productList);
        return modelAndView;
    }

    @RequestMapping("/showProducts")
    public ModelAndView showProducts(){
        List<CustomProduct> productList = productService.productList();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("productsList");
        return modelAndView;
    }

    @RequestMapping("/editProduct")
    public ModelAndView editProduct(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findProductByID(id);
        modelAndView.addObject("product", product);
        System.out.println(product.getPurchaseprice() + product.getSaleprice());
        return modelAndView;
    }

    @RequestMapping(value = "/editProductSubmit",method = RequestMethod.POST)
    public ModelAndView editProductSubmit(@ModelAttribute("product") Product product){
        ModelAndView modelAndView = new ModelAndView("message");
        productService.updateProduct(product);
        modelAndView.addObject("message","Edit Product Successfully");
        return modelAndView;
    }

    @RequestMapping("/preAdd")
    public ModelAndView preAdd(){
        return new ModelAndView("add");
    }

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


    @RequestMapping("/top")
    public String top(){
        return "top";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
