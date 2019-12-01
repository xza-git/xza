package com.fh.service;

import java.util.List;
import java.util.Map;

import com.fh.entity.BrandInfo;
import com.fh.utils.PageInfo;

public interface BrandService {

	//分页查询品牌
	PageInfo<BrandInfo> findBrandByPage(Map<String, Object> map);
	//根据品牌名查询品牌
	BrandInfo queryBrandByName(String brandName);
	//新增品牌
	void saveBrand(BrandInfo brandInfo);
	//删除品牌
	void deleteBrand(String id);
	//根据品牌id查询品牌
	BrandInfo queryBrandById(String id);
	//修改保存品牌
	void updateBrand(BrandInfo brandInfo);
	//查询品牌集合
	List<BrandInfo> queryBrandList();
}
