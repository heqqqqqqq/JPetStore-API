package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("inventory") //最好一个数据库对应一个实体类
public class ItemInventory {
    @TableId(value = "itemid",type= IdType.INPUT)
    private String itemId;
    @TableField(value = "qty")
    private int quantity;
}
