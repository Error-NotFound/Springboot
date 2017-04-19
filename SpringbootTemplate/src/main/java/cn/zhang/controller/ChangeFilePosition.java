package cn.zhang.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * dna1期c#版本导出内容导出到Dna1期java版所在文件夹下，并且目录格式正确,依照现场环境更改targetAdd和srouceAdd
 * @author zcm
 *
 */
public class ChangeFilePosition {
	//final static String targetAdd="C:\\project\\DNA\\协查\\";
	final static String targetAdd="E:\\project\\DNATest\\协查\\";
	final static String sourceAdd="E:\\Image";
	public static void changeFilePosition(File sourceInnerItem,File sourceFileItem,String type,String dicName) throws Exception{
		//copy到目标目录下
		String absoluteName=sourceInnerItem.getAbsolutePath();
		InputStream in=new FileInputStream(absoluteName);
		String tempExcelParentPath=targetAdd+sourceFileItem.getName()+"\\"+dicName;
		File tempExcelFilePath=new File(tempExcelParentPath);
		if(!tempExcelFilePath.exists()){
			tempExcelFilePath.mkdirs();
		}
		tempExcelParentPath+="\\"+sourceInnerItem.getName();//目标地址
		File fileTo=new File(tempExcelParentPath);//目标地址
		System.out.println(fileTo.getAbsolutePath()+"*****");
		if(!fileTo.exists()){
			fileTo.createNewFile();
		}
		OutputStream out=new FileOutputStream(fileTo);
		int ch=0;
		byte []bytes=new byte[1024];
		while((ch=in.read(bytes))!=-1){
		out.write(bytes);
		}
		in.close();
		out.close();
	}
	public static void main(String[] args) throws Exception {
		 File fileSource=new File(sourceAdd);
		 File []sourceFileAndDic=fileSource.listFiles();
		 for (int i = 0; i < sourceFileAndDic.length; i++) {
			File sourceFileItem=sourceFileAndDic[i];
			if(sourceFileItem.isDirectory()){
				File []sourceInnerFile=sourceFileItem.listFiles();
				for (int j = 0; j < sourceInnerFile.length; j++) {
					File sourceInnerItem=sourceInnerFile[j];//2010020202020
					if(sourceInnerItem.isDirectory()){//matcheFile
						File []innerFiles=sourceInnerItem.listFiles();
						//遍历这个目录
						for (int k = 0; k < innerFiles.length;k++) {
							//判断是否包含xls
							if(innerFiles[k].getName().contains(".xls")){
								//copy到目标目录下
								String absoluteName=innerFiles[k].getAbsolutePath();
								InputStream in=new FileInputStream(absoluteName);
								String tempExcelParentPath=targetAdd+sourceFileItem.getName()+"\\result";
								File tempExcelFilePath=new File(tempExcelParentPath);
								if(!tempExcelFilePath.exists()){
									tempExcelFilePath.mkdirs();
								}
								tempExcelParentPath+="\\"+innerFiles[k].getName();//目标地址
								File fileTo=new File(tempExcelParentPath);//目标地址
								System.out.println(fileTo.getAbsolutePath()+"*****");
								if(!fileTo.exists()){
									fileTo.createNewFile();
								}
								OutputStream out=new FileOutputStream(fileTo);
								int ch=0;
								byte []bytes=new byte[1024];
								while((ch=in.read(bytes))!=-1){
								out.write(bytes);
								}
								in.close();
								out.close();
							}
						}
					}else if(sourceInnerItem.getName().contains(".dat")){//复制dat文件到制定目录的sample下
						String type="dat";
						String dicName="sample";
						changeFilePosition(sourceInnerItem,sourceFileItem,type, dicName);
						/*//copy到目标目录下
						String absoluteName=sourceInnerItem.getAbsolutePath();
						InputStream in=new FileInputStream(absoluteName);
						String tempExcelParentPath="D:\\project\\DNA\\协查\\"+sourceFileItem.getName()+"\\sample";
						File tempExcelFilePath=new File(tempExcelParentPath);
						if(!tempExcelFilePath.exists()){
							tempExcelFilePath.mkdirs();
						}
						tempExcelParentPath+="\\"+sourceInnerItem.getName();//目标地址
						File fileTo=new File(tempExcelParentPath);//目标地址
						System.out.println(fileTo.getAbsolutePath()+"*****");
						if(!fileTo.exists()){
							fileTo.createNewFile();
						}
						OutputStream out=new FileOutputStream(fileTo);
						int ch=0;
						byte []bytes=new byte[1024];
						while((ch=in.read(bytes))!=-1){
						out.write(bytes);
						}
						in.close();
						out.close();*/
					}else{//其余当成附件存在制定目录的component
						String type="elseType";
						String dicName="component";
						changeFilePosition(sourceInnerItem,sourceFileItem,type, dicName);
						
					}
				}
			}else{
				//不是目录的话都放到pic下面手动导入
				
			}
		}
		 
		System.out.println(sourceFileAndDic.length+"个文件已经从"+sourceAdd+"导入到"+targetAdd+"！！！！！！！！！！！！！！！！！");
	}
}
