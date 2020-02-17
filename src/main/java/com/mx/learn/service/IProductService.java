package com.mx.learn.service;

import com.mx.learn.common.ServerResponse;
import com.mx.learn.pojo.Product;
import com.mx.learn.vo.ProductDetailVo;

/**
 * IProductService
 *
 * @author dagu29
 * @date 2020/2/16 0016
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> managerProductDetail(Integer productId);

}
