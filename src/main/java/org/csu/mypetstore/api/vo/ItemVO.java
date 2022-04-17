package org.csu.mypetstore.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVO {
    //Item表中的字段
    private String itemId;
    private String productId;//若数据库中为product_id可以与该字段对应
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplierId;
    private String status;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;

    //item所属product的属性
    private String categoryId;
    private String productName;
    private String productDescription;

    //item的库存，来自inventory表
    private int quantity;
}
