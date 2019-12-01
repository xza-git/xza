package com.fh.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.BrandMapper;
import com.fh.entity.BrandInfo;
import com.fh.service.BrandService;
import com.fh.utils.PageInfo;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandMapper brandDao;
	
	//查询用户列表 分页查询
		public PageInfo<BrandInfo> findBrandByPage(Map<String, Object> map) {
		
		//求出总条数
		Integer findCount = brandDao.findCount(map);
		Integer cpage=(Integer) map.get("cpage");
		Integer pageSize=(Integer) map.get("pageSize");
		PageInfo<BrandInfo> pageInfo = new PageInfo<BrandInfo>(cpage,pageSize);
		pageInfo.setTotalCount(findCount);
		//分页
		map.put("start", pageInfo.getStart());
		List<BrandInfo> findByPage = brandDao.findBrandByPage(map);
		pageInfo.setDataList(findByPage);
		return  pageInfo;
		
	}

	//根据品牌名称查询品牌
	public BrandInfo queryBrandByName(String brandName) {
		return brandDao.queryBrandByName(brandName);
	}
	//新增品牌
	public void saveBrand(BrandInfo brandInfo) {
		brandDao.saveBrand(brandInfo);
	}

	//删除品牌
	public void deleteBrand(String id) {
		brandDao.deleteBrand(id);
	}
	//根据品牌id查询品牌
	public BrandInfo queryBrandById(String aaa) {
		return brandDao.queryBrandById(aaa);
	}
	//修改保存品牌
	public void updateBrand(BrandInfo brandInfo) {
		brandDao.updateBrand(brandInfo);
	}
	//查询品牌集合
	public List<BrandInfo> queryBrandList() {
		return brandDao.queryBrandList();
	}
}
