package com.hu.base.credit.zip;

import com.hu.base.credit.response.FileUploadResponse;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 解压缩工具类
 *
 */
public class ZipUtil {
	
	/**
	 * 编码格式
	 */
	public final static String encoding = "GBK";

	/**
	 * 压缩文件
	 * @param srcFile（待压缩文件，必须是后缀为txt的文件，不能是文件夹）
	 * @param targetFile（压缩后的文件，必须是后缀为zip的文件，不能是文件夹）
	 * @return
	 */
	 public static FileUploadResponse zipFile(File srcFile,File targetFile) {
		 FileUploadResponse response = new FileUploadResponse();
		 if(srcFile==null || targetFile==null){
			 response.setSuccess(false);
			 response.setErrorMessage("待压缩文件或压缩后的文件不能为空!");
			 return response;
		 }
		 if(!srcFile.exists() || srcFile.isDirectory()){
			 response.setSuccess(false);
			 response.setErrorMessage("待压缩文件不存在或不能是文件夹!");
			 return response;
		 }
		 if(!srcFile.getName().endsWith(".txt") || !targetFile.getName().endsWith(".zip")){
			 response.setSuccess(false);
			 response.setErrorMessage("待压缩文件后缀必须为txt，压缩后的文件后缀必须为zip!");
			 return response;
		 }
		 if(targetFile.exists()){
			 targetFile.delete();
		 }

		 Project proj = new Project();
		 FileSet fileSet = new FileSet();
		 fileSet.setProject(proj);
		 fileSet.setFile(srcFile);
		
		 Zip zip = new Zip();
		 zip.setProject(proj);
		 zip.setDestFile(targetFile);
		 zip.addFileset(fileSet);
		 zip.setEncoding(encoding);
		 zip.execute();
		 return response;
    }
	 

	 /**
	 * 解压文件
	 * @param zipFile（压缩文件，必须是后缀为zip的文件，不能是文件夹）
	 * @param unzipFilePath（解压后文件的相对路径）
	 * @return
	 */
	 public static FileUploadResponse unzipFile(File zipFile,String unzipFilePath) {
		 FileUploadResponse response = new FileUploadResponse();
		 if(!zipFile.exists()){
		 	 response.setSuccess(false);
			 response.setErrorMessage("压缩文件不存在!");
			 return response;
		 }
		 if(!zipFile.getName().endsWith(".zip")){
		 	 response.setSuccess(false);
			 response.setErrorMessage("压缩文件后缀必须为zip");
			 return response;
		 }
		 Project proj = new Project();
		 Expand expand = new Expand();
		 expand.setProject(proj);
		 expand.setTaskType("unzip");
		 expand.setTaskName("unzip");
		 expand.setEncoding(encoding);
		
		 expand.setSrc(zipFile);
		 expand.setDest(new File(unzipFilePath));
		 expand.execute();
		 return response;

    }
	 
	 
	    
}
