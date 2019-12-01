package com.fh.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.fh.utils.AliyunOSSClientUtil;
import com.fh.utils.OSSClientConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;




@Controller
@RequestMapping("upload")
public class UploadController {
	
	@RequestMapping(value="uploadFile",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String uploadFile(MultipartFile file,HttpServletRequest request) throws IOException {
		//判断文件是否为空  isEmpty方法判断空为true 不空为false 所以加！
		if(!file.isEmpty()) {
			//初始化OSSClient
			OSSClient ossClient= AliyunOSSClientUtil.getOSSClient();
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentDisposition("inline; filename=" + file.getOriginalFilename());
			String fileName = UUID.randomUUID()+file.getOriginalFilename();
			PutObjectResult putObjectResult = ossClient.putObject(OSSClientConstants.BACKET_NAME, fileName, file.getInputStream(), meta);
			String eTag = putObjectResult.getETag();
			String str="https://" + OSSClientConstants.BACKET_NAME+"."+ OSSClientConstants.ENDPOINT+fileName;
			return "{\"json\":\"上传成功\",\"name\":\""+fileName+"\"}";
		}else {
			return "{\"json\":\"上传失败\",\"name\":\"\"}";
		}
		//正常上传至Tomcat
		/*
		 * MultipartHttpServletRequest request = (MultipartHttpServletRequest)req;
		 * MultipartFile file = request.getFile("file"); //判断文件是否为空 Boolean
		 * uploadFileType = false; String name=null; if(!file.isEmpty()){ //获取文件名 String
		 * file1 = file.getOriginalFilename(); //获取图片上传路径 String
		 * mainImagePath=req.getServletContext().getRealPath("/upload/poster/"); //创建文件夹
		 * File filePath = new File(mainImagePath); if(!filePath.exists()){
		 * filePath.mkdirs(); }
		 *
		 * //图片名
		 * name=UUID.randomUUID().toString()+file1.substring(file1.lastIndexOf("."));
		 * //生成一个32位不重复的uuid 加上 原本的文件后缀 前面加的是项目目录 File fileName = new
		 * File(mainImagePath+name); //上传文件到指定的目录下 try { file.transferTo(fileName); }
		 * catch (IllegalStateException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } uploadFileType = true; } if(uploadFileType){
		 * return "{\"json\":\"上传成功\",\"name\":\""+name+"\"}"; }else{ return
		 * "{\"json\":\"上传失败\",\"name\":\"\"}"; }
		 */

	}
}
