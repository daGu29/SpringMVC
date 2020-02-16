package com.mx.learn.service;

import com.mx.learn.common.ServerResponse;
import com.mx.learn.pojo.Category;

import java.util.List;

/**
 * ICategoryService
 *
 * @author dagu29
 * @date 2020/2/14 0014
 */
public interface ICategoryService {

    ServerResponse addCategory(String categoryName , Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId, String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse selectCategoryAndChildrenById(Integer categoryId);

}
