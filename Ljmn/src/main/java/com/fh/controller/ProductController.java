package com.fh.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.AddressInfo;
import com.fh.entity.BrandInfo;
import com.fh.entity.ProductInfo;
import com.fh.service.AddressService;
import com.fh.service.BrandService;
import com.fh.service.ProductService;
import com.fh.utils.FileUtil;
import com.fh.utils.PageInfo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	//注入service
	private ProductService productService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private AddressService addressService;
	
	//去商品列表页面
	@RequestMapping("toProductList")
	public String toProductList(){
		return "product/productList";
	}
	//去新增商品页面
	@RequestMapping("toSaveProductJsp")
	public ModelAndView toSaveProductJsp(ModelAndView mav){
		List<BrandInfo> list=brandService.queryBrandList();
		mav.setViewName("product/saveProduct");
		mav.addObject("brandList", list);
		return mav;
	}
	
	
	//导出excel
	@RequestMapping("derivedProduct")
	public void derivedProduct(String ids, HttpServletResponse arg1){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(",".equals(ids)){
			List<ProductInfo> list=productService.queryProductList();
			//创建workbook
			XSSFWorkbook xwk = new XSSFWorkbook();
			//创建sheet
			XSSFSheet createSheet = xwk.createSheet("商品表");
			createSheet.setColumnWidth(0, 20*400);
			createSheet.setColumnWidth(1, 20*400);
			createSheet.setColumnWidth(2, 20*400);
			createSheet.setColumnWidth(3, 20*200);
			createSheet.setColumnWidth(4, 20*400);
			createSheet.setColumnWidth(5, 20*400);
			//表头
			XSSFRow createRow1 = createSheet.createRow(0);
			//创建第一个单元格
			XSSFCell createCell1 = createRow1.createCell(0);
			createCell1.setCellValue("商品编号");
			XSSFCell createCell2 = createRow1.createCell(1);
			createCell2.setCellValue("商品名称");
			XSSFCell createCell3 = createRow1.createCell(2);
			createCell3.setCellValue("图片路径");
			XSSFCell createCell4 = createRow1.createCell(3);
			createCell4.setCellValue("商品单价");
			XSSFCell createCel = createRow1.createCell(4);
			createCel.setCellValue("创建时间");
			XSSFCell createCell5 = createRow1.createCell(5);
			createCell5.setCellValue("商品品牌");
			for (int i = 0; i < list.size(); i++) {
				XSSFRow createRow = createSheet.createRow(i+1);
				//创建第一个单元格
				XSSFCell createCel1 = createRow.createCell(0);
				createCel1.setCellValue(list.get(i).getId());
				XSSFCell createCel2 = createRow.createCell(1);
				createCel2.setCellValue(list.get(i).getProductName());
				XSSFCell createCel3 = createRow.createCell(2);
				createCel3.setCellValue(list.get(i).getMainImagePath());
				XSSFCell createCel4 = createRow.createCell(3);
				createCel4.setCellValue(list.get(i).getPrice());
				XSSFCell createCe = createRow.createCell(4);
				createCe.setCellValue(sim.format(list.get(i).getCreateDate()));
				XSSFCell createCel5 = createRow.createCell(5);
				createCel5.setCellValue(list.get(i).getBrandName());
			}
			FileUtil.excelDownload(xwk, arg1);
		}else {
			List<ProductInfo> list=new ArrayList<ProductInfo>();
			String[] split = ids.split(",");
			for (int i = 1; i < split.length; i++) {
				ProductInfo product = productService.queryProductById(split[i]);
				list.add(product);
			}
			//创建workbook
			XSSFWorkbook xwk = new XSSFWorkbook();
			//创建sheet
			XSSFSheet createSheet = xwk.createSheet("商品表");
			createSheet.setColumnWidth(0, 20*400);
			createSheet.setColumnWidth(1, 20*400);
			createSheet.setColumnWidth(2, 20*400);
			createSheet.setColumnWidth(3, 20*200);
			createSheet.setColumnWidth(4, 20*400);
			createSheet.setColumnWidth(5, 20*400);
			//表头
			XSSFRow createRow1 = createSheet.createRow(0);
			//创建第一个单元格
			XSSFCell createCell1 = createRow1.createCell(0);
			createCell1.setCellValue("商品编号");
			XSSFCell createCell2 = createRow1.createCell(1);
			createCell2.setCellValue("商品名称");
			XSSFCell createCell3 = createRow1.createCell(2);
			createCell3.setCellValue("图片路径");
			XSSFCell createCell4 = createRow1.createCell(3);
			createCell4.setCellValue("商品单价");
			XSSFCell createCel = createRow1.createCell(4);
			createCel.setCellValue("创建时间");
			XSSFCell createCell5 = createRow1.createCell(5);
			createCell5.setCellValue("商品品牌");
			for (int i = 0; i < list.size(); i++) {
				XSSFRow createRow = createSheet.createRow(i+1);
				//创建第一个单元格
				XSSFCell createCel1 = createRow.createCell(0);
				createCel1.setCellValue(list.get(i).getId());
				XSSFCell createCel2 = createRow.createCell(1);
				createCel2.setCellValue(list.get(i).getProductName());
				XSSFCell createCel3 = createRow.createCell(2);
				createCel3.setCellValue(list.get(i).getMainImagePath());
				XSSFCell createCel4 = createRow.createCell(3);
				createCel4.setCellValue(list.get(i).getPrice());
				XSSFCell createCe = createRow.createCell(4);
				createCe.setCellValue(sim.format(list.get(i).getCreateDate()));
				XSSFCell createCel5 = createRow.createCell(5);
				createCel5.setCellValue(list.get(i).getBrandName());
			}
			FileUtil.excelDownload(xwk, arg1);
		}
	}
	
	@RequestMapping("derivedPDF")
	public void derivedPDF(HttpServletResponse response, String ids){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(ids == ",") {
			//定义一个字节流数组
			ByteArrayOutputStream byo = new ByteArrayOutputStream();
			//查询到数据集合
			List<ProductInfo> list = productService.queryProductList();
			//创建一个doucment对象 文本对象
			Document document = new Document();
			document.setPageSize(PageSize.A4);
			//创建字体 设置为中文
			BaseFont font = null;
			try {
				font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//创建列的字体样式
			Font keyFont = new Font(font, 10,Font.BOLD);
			//创建pdf文件
			try {
				PdfWriter.getInstance(document, byo);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//设置一个表头数组
			String[] str = {"商品名称","商品品牌","商品图片","商品单价","添加时间"};
			//设置书写器
			PdfPTable table = FileUtil.createTable(str.length);
			//打开文本对象
			document.open();
			//设置标题
			Font headFont = new Font(font,25,Font.BOLD);
			//设置标题的颜色
			headFont.setColor(BaseColor.RED);
			//创建标题
			PdfPCell headCell = FileUtil.createHeadline("商品列表", headFont);
			headCell.setExtraParagraphSpace(30);
			//放入书写器
			table.addCell(headCell);
			for (int i = 0; i < str.length; i++) {
				table.addCell(FileUtil.createCell(str[i], keyFont, Element.ALIGN_CENTER));
			}
			//把查询到的数据集合遍历到书写器里面
			for (int i = 0; i < list.size(); i++) {
				table.addCell(FileUtil.createCell(list.get(i).getProductName(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getBrandName(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getMainImagePath(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getPrice().toString(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(sim.format(list.get(i).getCreateDate()), keyFont, Element.ALIGN_CENTER));
			}
			//放入文本对象
			try {
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			FileUtil.pdfDownload(response, byo);
		}else {
			//查询到数据集合
			List<ProductInfo> list= new ArrayList<ProductInfo>();
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				if(split[i] !=null && !"".equals(split[i])) {
					list.add(productService.queryProductById(split[i]));
				}
			}
			//定义一个字节流数组
			ByteArrayOutputStream byo = new ByteArrayOutputStream();
			//创建一个doucment对象 文本对象
			Document document = new Document();
			document.setPageSize(PageSize.A4);
			//创建字体 设置为中文
			BaseFont font = null;
			try {
				font = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//创建列的字体样式
			Font keyFont = new Font(font, 10,Font.BOLD);
			//创建pdf文件
			try {
				PdfWriter.getInstance(document, byo);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//设置一个表头数组
			String[] str = {"商品名称","商品品牌","商品图片","商品单价","添加时间"};
			//设置书写器
			PdfPTable table = FileUtil.createTable(str.length);
			//打开文本对象
			document.open();
			//设置标题
			Font headFont = new Font(font,25,Font.BOLD);
			//设置标题的颜色
			headFont.setColor(BaseColor.RED);
			//创建标题
			PdfPCell headCell = FileUtil.createHeadline("商品列表", headFont);
			headCell.setExtraParagraphSpace(30);
			//放入书写器
			table.addCell(headCell);
			for (int i = 0; i < str.length; i++) {
				table.addCell(FileUtil.createCell(str[i], keyFont, Element.ALIGN_CENTER));
			}
			//把查询到的数据集合遍历到书写器里面
			for (int i = 0; i < list.size(); i++) {
				table.addCell(FileUtil.createCell(list.get(i).getProductName(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getBrandName(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getMainImagePath(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(list.get(i).getPrice().toString(), keyFont, Element.ALIGN_CENTER));
				table.addCell(FileUtil.createCell(sim.format(list.get(i).getCreateDate()), keyFont, Element.ALIGN_CENTER));
			}
			//放入文本对象
			try {
				document.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
			FileUtil.pdfDownload(response, byo);
		}
	}
	
	//查询商品列表 分页查询
	@RequestMapping("findProductByPage")
	@ResponseBody
	public PageInfo<ProductInfo> findUserByPage(Integer pageSize,Integer cpage,String productName,String beginDate, String endDate){
		Map<String, Object> map=new HashMap<String, Object>();
		/*
		 * map.put("beginDate", beginDate); map.put("endDate", endDate);
		 */
		System.out.println(beginDate+endDate);
		map.put("productName", productName);
		map.put("pageSize", pageSize);
		map.put("cpage", cpage);
		PageInfo<ProductInfo> pageInfo= productService.findProductByPage(map);
		return pageInfo;
	}
	
	
	//新增商品
	@RequestMapping(value="saveProduct",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String saveProduct(ProductInfo productInfo,Integer province,Integer city,Integer county,String address){
		//通过用户名查询用户 验证用户是否存在
		ProductInfo product=productService.queryProductByName(productInfo.getProductName());
		if(product==null){
			//新增保存用户
			productInfo.setCreateDate(new Date());
			String s=province+","+city+","+county+","+address;
			productInfo.setAddress(s);
			productService.saveProduct(productInfo);
			return "{\"success\":\"1\"}";
		}else{
			return "{\"success\":\"2\"}";
		}
	}
	
	
	//删除商品
	@RequestMapping(value="deleteProduct",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteProduct(String id){
		productService.deleteProduct(id);
		return "{\"success\":\"删除成功\"}";
	}
	//去修改页面
	@RequestMapping("updateJsp")
	public ModelAndView updateJsp(String aaa,ModelAndView mav){
		ProductInfo productInfo=productService.queryProductById(aaa);
		List<BrandInfo> brandList=brandService.queryBrandList();
		String[] split = productInfo.getAddress().split(",");
		AddressInfo province = addressService.queryAddressById(Integer.parseInt(split[0]));
		AddressInfo city = addressService.queryAddressById(Integer.parseInt(split[1]));
		AddressInfo county = addressService.queryAddressById(Integer.parseInt(split[2]));
		List<AddressInfo> addressList = addressService.queryAddressLists();
		mav.setViewName("product/updateProduct");
		mav.addObject("brandList", brandList);
		mav.addObject("productInfo", productInfo);
		mav.addObject("province", province);
		mav.addObject("city", city);
		mav.addObject("county", county);
		mav.addObject("strs", split[3]);
		mav.addObject("addressList", addressList);
		return mav;
	}
	
	//修改保存商品
	@RequestMapping("updateProduct")
	public String updateProduct(ProductInfo productInfo,Integer province,Integer city,Integer county,String address){
		String s=province+","+city+","+county+","+address;
		productInfo.setAddress(s);
		productService.updateProduct(productInfo);
		return "redirect:toProductList";
	}
}
