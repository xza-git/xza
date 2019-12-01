package com.fh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.BrandInfo;
import com.fh.service.BrandService;
import com.fh.utils.PageInfo;

@Controller
@RequestMapping("brand")
public class BrandController {
	
	@Autowired
	//注入service
	private BrandService brandService;

	
	//去品牌列表页面
	@RequestMapping("toBrandList")
	public String toBrandList(){
		return "brand/brandList";
	}
	//去新增品牌页面
	@RequestMapping("toSaveBrandJsp")
	public String toSaveBrandJsp(){
		return "brand/saveBrand";
	}
	
	//查询用户列表 分页查询
	@RequestMapping("findBrandByPage")
	@ResponseBody
	public PageInfo<BrandInfo> findBrandByPage(Integer pageSize,Integer cpage,String brandName){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("brandName", brandName);
		map.put("pageSize", pageSize);
		map.put("cpage", cpage);
		PageInfo<BrandInfo> pageInfo= brandService.findBrandByPage(map);
		return pageInfo;
	}
	
	
	//新增品牌
	@RequestMapping(value="saveBrand",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveBrand(BrandInfo brandInfo){
		//通过品牌名查询品牌 验证品牌是否存在
		BrandInfo brand=brandService.queryBrandByName(brandInfo.getBrandName());
		if(brand==null){
			//新增保存用户
			brandInfo.setCreateDate(new Date());
			brandService.saveBrand(brandInfo);
			return "{\"success\":\"1\"}";
		}else{
			return "{\"success\":\"2\"}";
		}
	}
	
	
	//删除品牌
	@RequestMapping(value="deleteBrand",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteBrand(String id){
		brandService.deleteBrand(id);
		return "{\"success\":\"删除成功\"}";
	}
	
	@RequestMapping("updateJsp")
	public ModelAndView updateJsp(String aaa,ModelAndView mav){
		BrandInfo brandInfo=brandService.queryBrandById(aaa);
		mav.setViewName("brand/updateBrand");
		mav.addObject("brandInfo", brandInfo);
		return mav;
	}
	
	//修改保存品牌
	@RequestMapping("updateBrand")
	public String updateBrand(BrandInfo brandInfo){
		brandService.updateBrand(brandInfo);
		return "redirect:toBrandList";
	}
}