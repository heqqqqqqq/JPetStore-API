package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.Category;
import org.csu.mypetstore.api.entity.Item;
import org.csu.mypetstore.api.entity.ItemInventory;
import org.csu.mypetstore.api.entity.Product;
import org.csu.mypetstore.api.persistence.CategoryMapper;
import org.csu.mypetstore.api.persistence.ItemInventoryMapper;
import org.csu.mypetstore.api.persistence.ItemMapper;
import org.csu.mypetstore.api.persistence.ProductMapper;
import org.csu.mypetstore.api.service.CatalogService;
import org.csu.mypetstore.api.vo.ItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CommonResponse<List<Category>> getCategoryList() {
        List<Category> categoryList=categoryMapper.selectList(null);
        if(categoryList.isEmpty()){//mybatis-plus如果没有查到信息不会报错，而是返回0条记录，故增加一个判错机制
            return CommonResponse.createForSuccessMessage("没有分类信息");//查询成功，但是没有信息
        }
        return CommonResponse.createForSuccess(categoryList);
    }

    @Override
    public CommonResponse<Category> getCategory(String categoryId) {
        Category category=categoryMapper.selectById(categoryId);
        if(category==null){//判错
            return CommonResponse.createForSuccessMessage("没有该id的category");
        }
        return CommonResponse.createForSuccess(category);
    }

    @Autowired
    private ProductMapper productMapper;

    @Override
    public CommonResponse<List<Product>> getProductListByCategoryId(String categoryId) {
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("category",categoryId);//category是数据库中product表的属性
        List<Product> productList=productMapper.selectList(queryWrapper);

        if(productList.isEmpty()){
            return CommonResponse.createForSuccessMessage("该分类下没有Product子类");
        }
        return CommonResponse.createForSuccess(productList);
    }

    @Override
    public CommonResponse<Product> getProductById(String productId) {
        Product product=productMapper.selectById(productId);
        if(product==null){
            return CommonResponse.createForSuccessMessage("没有该Id的product");
        }
        return CommonResponse.createForSuccess(product);
    }

    @Autowired
    ItemMapper itemMapper;
    @Autowired
    ItemInventoryMapper itemInventoryMapper;

    @Override
    public CommonResponse<List<ItemVO>> getItemsByProductId(String productId) {
        QueryWrapper<Item> queryWrapper=new QueryWrapper<>();//查询构造器
        queryWrapper.eq("productid",productId);
        List<Item> itemList=itemMapper.selectList(queryWrapper);
        if(itemList.isEmpty()){
            return CommonResponse.createForSuccessMessage("该product下没有item商品");
        }

        Product product=productMapper.selectById(productId);
        List<ItemVO> itemVOList=new ArrayList<>();

        //对于每一个item都要转换，故使用循环
        for(Item item:itemList){
            ItemVO itemVO=itemToItemVO(item,product);
            itemVOList.add(itemVO);
        }
        return CommonResponse.createForSuccess(itemVOList);
    }

    @Override
    public CommonResponse<ItemVO> getItemById(String itemId) {
        Item item=itemMapper.selectById(itemId);
        if(item==null){
            return CommonResponse.createForSuccessMessage("没有该Id的item");
        }
        Product product=productMapper.selectById(item.getProductId());
        ItemVO itemVO=itemToItemVO(item,product);
        return CommonResponse.createForSuccess(itemVO);
    }

    //itemVO拼接
    private ItemVO itemToItemVO(Item item,Product product){
        ItemVO itemVO=new ItemVO();
        itemVO.setItemId(item.getItemId());
        itemVO.setProductId(item.getProductId());
        itemVO.setListPrice(item.getListPrice());
        itemVO.setUnitCost(item.getUnitCost());
        itemVO.setSupplierId(item.getSupplierId());
        itemVO.setStatus(item.getStatus());
        itemVO.setAttribute1(item.getAttribute1());
        itemVO.setAttribute2(item.getAttribute2());
        itemVO.setAttribute3(item.getAttribute3());
        itemVO.setAttribute4(item.getAttribute4());
        itemVO.setAttribute5(item.getAttribute5());

        itemVO.setCategoryId(product.getCategoryId());
        itemVO.setProductName(product.getName());
        itemVO.setProductDescription(product.getDescription());

        ItemInventory itemInventory=itemInventoryMapper.selectById(item.getItemId());
        itemVO.setQuantity(itemInventory.getQuantity());

        return itemVO;
    }
}
