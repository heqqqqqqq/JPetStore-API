package org.csu.mypetstore.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("category")  //使用mybatis-plus需要指定该实体类对应的表名，防止因为表名不一致导致无法匹配
public class Category {
    @TableId(value = "catid",type= IdType.INPUT)//主键
    private String categoryId;
    @TableField(value = "name")
    private String name;
    @TableField(value = "descn")
    private String description;
}
