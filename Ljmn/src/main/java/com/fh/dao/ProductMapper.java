package com.fh.dao;

import java.util.List;
import java.util.Map;

import com.fh.entity.AddressInfo;
import com.fh.entity.ProductInfo;

public interface ProductMapper {
	//分页查询总条数
	Integer findCount(Map<String, Object> map);
	//分页查询商品
	List<ProductInfo> findProductByPage(Map<String, Object> map);
	//根据商品名查询商品
	ProductInfo queryProductByName(String productName);
	//新增商品
	void saveProduct(ProductInfo productInfo);
	//删除商品
	void deleteProduct(String id);
	//根据商品id查询商品
	ProductInfo queryProductById(String aaa);
	//修改保存商品
	void updateProduct(ProductInfo productInfo);
	//查询商品列表
	List<ProductInfo> queryProductList();
	//查询省市县中省的集合
	List<AddressInfo> queryAddressList(Integer pid);


}
