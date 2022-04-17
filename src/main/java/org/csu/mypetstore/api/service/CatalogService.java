package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.vo.ItemVO;

import java.util.List;

public interface CatalogService {
    //category
    CommonResponse<List<Category>> getCategoryList();//获取所有种类
    CommonResponse<Category> getCategory(String categoryId);//根据Id获取单个category
    //product
    CommonResponse<List<Product>> getProductListByCategoryId(String categoryId);//获取某个category的所有product种类
    CommonResponse<Product> getProductById(String productId);//获取单个product
    //item
    CommonResponse<List<ItemVO>> getItemsByProductId(String productId);
    CommonResponse<ItemVO> getItemById(String itemId);
}
