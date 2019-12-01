package com.fh.dao;

import java.util.List;
import java.util.Map;

import com.fh.entity.BrandInfo;

public interface BrandMapper {

	//分页查询总条数
	Integer findCount(Map<String, Object> map);
	//分页查询品牌
	List<BrandInfo> findBrandByPage(Map<String, Object> map);
	//根据品牌名查询品牌
	BrandInfo queryBrandByName(String brandName);
	//新增品牌
	void saveBrand(BrandInfo brandInfo);
	//删除品牌
	void deleteBrand(String id);
	//根据品牌id查询品牌
	BrandInfo queryBrandById(String aaa);
	//修改保存品牌
	void updateBrand(BrandInfo brandInfo);
	//查询品牌集合
	List<BrandInfo> queryBrandList();
}
