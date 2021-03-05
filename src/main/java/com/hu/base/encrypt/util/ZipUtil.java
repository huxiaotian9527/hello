package com.hu.base.encrypt.util;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;
import com.hu.base.encrypt.response.FileUploadResponse;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

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
	 public static FileUploadResponse unzipFile(File zipFile,String unzipFilePath)throws Exception {
		 FileUploadResponse response = new FileUploadResponse();
		 if(!zipFile.exists()){
		 	 response.setSuccess(false);
			 response.setErrorMessage("压缩文件不存在!");
			 return response;
		 }
//		 if(!zipFile.getName().endsWith(".zip")){
//		 	 response.setSuccess(false);
//			 response.setErrorMessage("压缩文件后缀必须为zip");
//			 return response;
//		 }
		 Project proj = new Project();
		 Expand expand = new Expand();
		 expand.setProject(proj);
		 expand.setTaskType("unzip");
		 expand.setTaskName("unzip");
		 expand.setEncoding(encoding);
		
		 expand.setSrc(zipFile);
		 File x = new File("D://132//");
		 expand.setDest(x);
		 expand.execute();
		 if(x.exists()){
		 	x.createNewFile();
		 }
		 return response;

    }

	public static void unRar(File rarFile, String outDir) throws Exception {
		File outFileDir = new File(outDir);
		if (!outFileDir.exists()) {
			boolean isMakDir = outFileDir.mkdirs();
			if (isMakDir) {
				System.out.println("创建压缩目录成功");
			}
		}
		Archive archive = new Archive(new FileInputStream(rarFile));
		FileHeader fileHeader = archive.nextFileHeader();
		while (fileHeader != null) {
			if (fileHeader.isDirectory()) {
				fileHeader = archive.nextFileHeader();
				continue;
			}
			File out = new File(outDir + fileHeader.getFileNameString());
			if (!out.exists()) {
				if (!out.getParentFile().exists()) {
					out.getParentFile().mkdirs();
				}
				out.createNewFile();
			}
			FileOutputStream os = new FileOutputStream(out);
			archive.extractFile(fileHeader, os);
			os.close();
			fileHeader = archive.nextFileHeader();
		}
		archive.close();
	}

	public static File unRar1(File rarFile) throws Exception {
		Archive archive = new Archive(new FileInputStream(rarFile));
		FileHeader fileHeader = archive.nextFileHeader();
		File out = new File(rarFile.getName().replace("rar", "txt"));
		FileOutputStream os = new FileOutputStream(out);
		archive.extractFile(fileHeader, os);
		os.close();
		archive.nextFileHeader();
		archive.close();
		return out;
	}

	public static void main(String[] args) throws Exception{
	 	File zipFile = new File("D://EF70202493464A94ADE035944754F94B.rar");
//		ZipUtil.unzipFile(zipFile,"");
//		ZipUtil.realExtract(zipFile,"D://123//");
//		ZipUtil.unRar(zipFile,"D://123//");

		realExtract(zipFile,zipFile.getAbsolutePath().replace(zipFile.getName(),""));
	}

	/**
	 * 采用命令行方式解压文件
	 * @param zipFile 压缩文件
	 * @param destDir 解压结果路径
	 * @return
	 */
//	public static boolean realExtract(File zipFile, String destDir) {
//		// 解决路径中存在/..格式的路径问题
//		destDir = new File(destDir).getAbsoluteFile().getAbsolutePath();
//		while(destDir.contains("..")) {
//			String[] sepList = destDir.split("\\\\");
//			destDir = "";
//			for (int i = 0; i < sepList.length; i++) {
//				if(!"..".equals(sepList[i]) && i < sepList.length -1 && "..".equals(sepList[i+1])) {
//					i++;
//				} else {
//					destDir += sepList[i] + File.separator;
//				}
//			}
//		}
//
//		boolean bool = false;
//		if (!zipFile.exists()) {
//			return false;
//		}
//
//		// 开始调用命令行解压，参数-o+是表示覆盖的意思
//		String cmdPath = "D:\\tools\\WinRAR\\WinRAR.exe";
//		String cmd = cmdPath + " X -o+ " + zipFile + " " + destDir;
//		System.out.println(cmd);
//		try {
//			Process proc = Runtime.getRuntime().exec(cmd);
//			if (proc.waitFor() != 0) {
//				if (proc.exitValue() == 0) {
//					bool = false;
//				}
//			} else {
//				bool = true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("解压" + (bool ? "成功" : "失败"));
//		return bool;
//	}
	public static boolean realExtract(File zipFile, String destDir) {
		// 解决路径中存在/..格式的路径问题
		destDir = new File(destDir).getAbsoluteFile().getAbsolutePath();
		boolean bool = false;
		if (!zipFile.exists()) {
			return false;
		}
		// 开始调用命令行解压，参数-o+是表示覆盖的意思
		String cmdPath = "D:\\tools\\WinRAR\\WinRAR.exe";
		String cmd = cmdPath + " X -o+ " + zipFile + " " + destDir;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			if (proc.waitFor() != 0) {
				if (proc.exitValue() == 0) {
					bool = false;
				}
			} else {
				bool = true;
			}
		} catch (Exception e) {}
		return bool;
	}
}
