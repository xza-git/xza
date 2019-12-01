package com.fh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.ProductMapper;
import com.fh.entity.AddressInfo;
import com.fh.entity.ProductInfo;
import com.fh.service.AddressService;
import com.fh.service.ProductService;
import com.fh.utils.PageInfo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productDao;
	@Autowired
	private AddressService addressService;

	//查询用户列表 分页查询
	public PageInfo<ProductInfo> findProductByPage(Map<String, Object> map) {
		
		//求出总条数
		Integer findCount = productDao.findCount(map);
		Integer cpage=(Integer) map.get("cpage");
		Integer pageSize=(Integer) map.get("pageSize");
		PageInfo<ProductInfo> pageInfo = new PageInfo<ProductInfo>(cpage,pageSize);
		pageInfo.setTotalCount(findCount);
		//分页
		map.put("start", pageInfo.getStart());
		List<ProductInfo> findByPage = productDao.findProductByPage(map);
		for (int i = 0; i < findByPage.size(); i++) {
			String address=findByPage.get(i).getAddress();
			if (address != null && address != "") {
				String[] addressArr=address.split(",");
				String addressName="";
				for (int j = 0; j < addressArr.length-1; j++) {
					AddressInfo city=addressService.queryAddressById(Integer.parseInt(addressArr[j]));
					addressName += city.getName();
				}
				findByPage.get(i).setAddress(addressName+addressArr[addressArr.length-1]);
			}
		}
		pageInfo.setDataList(findByPage);
		return  pageInfo;
		
	}

	//根据商品名称查询商品
	public ProductInfo queryProductByName(String productName) {
		return productDao.queryProductByName(productName);
	}
	//新增商品
	public void saveProduct(ProductInfo productInfo) {
		productDao.saveProduct(productInfo);
	}

	//删除商品
	public void deleteProduct(String id) {
		productDao.deleteProduct(id);
	}
	//根据商品id查询商品
	public ProductInfo queryProductById(String aaa) {
		return productDao.queryProductById(aaa);
	}
	//修改保存商品
	public void updateProduct(ProductInfo productInfo) {
		productDao.updateProduct(productInfo);
	}

	//查询商品列表
	public List<ProductInfo> queryProductList() {
		return productDao.queryProductList();
	}

	//查询省市县中省的集合
	public List<AddressInfo> queryAddressList(Integer pid) {
		return productDao.queryAddressList(pid);
	}
	
}
