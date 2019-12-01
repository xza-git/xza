package com.fh.utils;

import java.util.ArrayList;
import java.util.List;


public class PageInfo<T>{
	private int cpage;//当前页
	private int pageSize;//每页条数
	private int totalCount;//总条数
	private int startPage;//开始页数
	private int totalPage;//总页数
	private int start;
	private List<T> dataList = new ArrayList<T>();//数据集合
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalPage = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public PageInfo(Integer cpage,Integer pageSize ){
		if(cpage == null|| cpage < 1){
			cpage=1;
		}
		if(pageSize==null || pageSize <1){
			pageSize = 2;
		}
		this.cpage = cpage;
		this.pageSize = pageSize;
		this.start = (this.cpage - 1) * this.pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	
}
