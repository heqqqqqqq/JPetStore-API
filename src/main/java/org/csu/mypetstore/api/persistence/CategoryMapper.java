package org.csu.mypetstore.api.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.mypetstore.api.entity.Category;
import org.springframework.stereotype.Repository;

//直接使用BaseMapper,不用编写映射文件
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
}
