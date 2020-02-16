package com.mx.learn.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mx.learn.common.ServerResponse;
import com.mx.learn.dao.CategoryMapper;
import com.mx.learn.pojo.Category;
import com.mx.learn.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * CategoryServiceImpl
 *
 * @author dagu29
 * @date 2020/2/14 0014
 */
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName, Integer parentId) {
        if (parentId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("添加品类参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);

        int rowCount = categoryMapper.insert(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("添加品类成功");
        }
        return ServerResponse.createByErrorMessage("添加品类失败");
    }

    @Override
    public ServerResponse updateCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null || StringUtils.isBlank(categoryName)) {
            return ServerResponse.createByErrorMessage("更新品类参数错误");
        }
        Category category = new Category();
        category.setId(categoryId);
        category.setName(categoryName);

        int rowCount = categoryMapper.updateByPrimaryKeySelective(category);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新品类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名字失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId) {
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        if (CollectionUtils.isEmpty(categoryList)) {
            logger.info("未找到当前类的子类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    /**
     * 递归查询本节点的ID和孩子节点的ID
     * @param categoryId
     * @return
     */
    @Override
    public ServerResponse selectCategoryAndChildrenById(Integer categoryId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

//        List<Integer> list = Lists.newArrayList();
//        if (categoryId != null) {
//            for (Category category : categorySet) {
//                list.add(category.getId());
//            }
//        }

        List<Integer> categoryIdList = categorySet.stream().map(Category::getId).collect(Collectors.toList());
        return ServerResponse.createBySuccess(categoryIdList);
    }

    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId) {
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null) {
            categorySet.add(category);
        }
        //要查子节点，递归算法一定要有一个结束条件
        List<Category> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
        for (Category categoryItem : categoryList) {
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }
}
