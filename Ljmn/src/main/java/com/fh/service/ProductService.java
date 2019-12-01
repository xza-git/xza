package com.fh.service;

import java.util.List;
import java.util.Map;

import com.fh.entity.AddressInfo;
import com.fh.entity.ProductInfo;
import com.fh.utils.PageInfo;

public interface ProductService {
	//分页查询商品
	PageInfo<ProductInfo> findProductByPage(Map<String, Object> map);
	//根据商品名查询商品
	ProductInfo queryProductByName(String productName);
	//新增商品
	void saveProduct(ProductInfo productInfo);
	//删除商品
	void deleteProduct(String id);
	//根据商品id查询商品
	ProductInfo queryProductById(String id);
	//修改保存商品
	void updateProduct(ProductInfo productInfo);
	//查询商品列表
	List<ProductInfo> queryProductList();
	//查询省市县中省的集合
	List<AddressInfo> queryAddressList(Integer pid);
}
