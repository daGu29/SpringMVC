package com.mx.learn.service.impl;

import com.mx.learn.common.ResponseCode;
import com.mx.learn.common.ServerResponse;
import com.mx.learn.dao.CategoryMapper;
import com.mx.learn.dao.ProductMapper;
import com.mx.learn.pojo.Category;
import com.mx.learn.pojo.Product;
import com.mx.learn.service.IProductService;
import com.mx.learn.util.DateTimeUtil;
import com.mx.learn.util.PropertiesUtil;
import com.mx.learn.vo.ProductDetailVo;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductServiceImpl
 *
 * @author dagu29
 * @date 2020/2/16 0016
 */
@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse saveOrUpdateProduct(Product product) {
        if (product != null) {
            if (StringUtils.isNotBlank(product.getSubImages())) {
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0) {
                    product.setMainImage(subImageArray[0]);
                }
                if (product.getId() != null) {
                    int rowCount = productMapper.updateByPrimaryKey(product);
                    if (rowCount > 0) {
                        return ServerResponse.createBySuccessMessage("更新产品成功");
                    }
                    return ServerResponse.createByErrorMessage("更新产品失败");
                } else {
                    int rowCount = productMapper.insert(product);
                    if (rowCount > 0) {
                        return ServerResponse.createBySuccessMessage("更新产品成功");
                    }
                    return ServerResponse.createByErrorMessage("更新产品失败");
                }
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品数据不正确");
    }

    @Override
    public ServerResponse<String> setSaleStatus(Integer productId, Integer status) {
        if (productId == null || status == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);
        int rowCount = productMapper.updateByPrimaryKeySelective(product);
        if (rowCount > 0) {
            return ServerResponse.createBySuccessMessage("修改产品销售状态成功");
        }
        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }

    @Override
    public ServerResponse<ProductDetailVo> managerProductDetail(Integer productId) {
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = productMapper.selectByPrimaryKey(productId);
        if (product != null) {
            ProductDetailVo productDetailVo = assembleDetailVo(product);
            return ServerResponse.createBySuccess(productDetailVo);
        }
        return ServerResponse.createByErrorMessage("产品已下架或者删除");
    }

    private ProductDetailVo assembleDetailVo(Product product) {
        ProductDetailVo detailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, detailVo);

        detailVo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix", "http://img.happymmall.com/"));

        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        //空 默认根节点
        detailVo.setParentCategoryId(category != null ? category.getParentId() : 0);

        //createTime  updateTime
        detailVo.setCreateTime(DateTimeUtil.date2Str(product.getCreateTime()));
        detailVo.setUpdateTime(DateTimeUtil.date2Str(product.getUpdateTime()));
        return detailVo;
    }
}