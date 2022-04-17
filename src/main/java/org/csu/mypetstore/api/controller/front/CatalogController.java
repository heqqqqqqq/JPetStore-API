package org.csu.mypetstore.api.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.service.CatalogService;
import org.csu.mypetstore.api.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("categories")
    @ResponseBody   //将返回的对象以JSON的形式传输给前端
    public CommonResponse<List<Category>> getCategoryList(){
        return catalogService.getCategoryList();
    }

    @GetMapping("categories/{id}")
    @ResponseBody
    public CommonResponse<Category> getCategory(@PathVariable("id") String categoryId){
        return catalogService.getCategory(categoryId);
    }

    @GetMapping("categories/{id}/products")
    @ResponseBody
    public CommonResponse<List<Product>> getProductListByCategoryId(@PathVariable("id") String categoryId){
        return catalogService.getProductListByCategoryId(categoryId);
    }

    @GetMapping("products/{id}")
    @ResponseBody
    public CommonResponse<Product> getProductById(@PathVariable("id")String productId){
        return catalogService.getProductById(productId);
    }

    @GetMapping("products/{id}/items")
    @ResponseBody
    public CommonResponse<List<ItemVO>> getItemsByProductId(@PathVariable("id")String productId){
        return catalogService.getItemsByProductId(productId);
    }

    @GetMapping("items/{id}")
    @ResponseBody
    public CommonResponse<ItemVO> getItemById(@PathVariable("id") String itemId){
        return catalogService.getItemById(itemId);
    }
}
