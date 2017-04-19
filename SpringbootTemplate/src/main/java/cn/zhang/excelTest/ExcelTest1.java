package cn.zhang.excelTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.zhang.model.IdCard;

public class ExcelTest1 {
	public static void main(String[] args) throws Exception{
		writeData();
	}
	public static void newHSSFWorkbook()throws Exception{
		Workbook wb=new HSSFWorkbook();
		FileOutputStream fileOut=new FileOutputStream("e:"+File.separator+"test1.xls");
		wb.write(fileOut);
		fileOut.close();
	}
	public static void newXSSFWorkbook()throws Exception{
		Workbook wb=new XSSFWorkbook();
		FileOutputStream fileOut=new FileOutputStream("e:"+File.separator+"test2.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}
	public static void newSheet()throws Exception{
		Workbook wb=new XSSFWorkbook();
		Sheet sheet1=wb.createSheet("new sheet");
		Sheet sheet2=wb.createSheet("second sheet");
		String safeSheetName=WorkbookUtil.createSafeSheetName("[O'Brien's sales*?]");
		Sheet sheet3=wb.createSheet(safeSheetName);
		FileOutputStream fileOut=new FileOutputStream("e:"+File.separator+"test2.xlsx");
		wb.write(fileOut);
		fileOut.close();
	}
	public static void createCell()throws Exception{
		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper=wb.getCreationHelper();
		Sheet sheet=wb.createSheet("new sheet");
		Row row=sheet.createRow((short)0);
		Cell cell=row.createCell(0);
		cell.setCellValue(111);
		row.createCell(1).setCellValue(1.2);
	    row.createCell(2).setCellValue(
	         createHelper.createRichTextString("This is a string"));
	    row.createCell(3).setCellValue(true);
	    FileOutputStream fileOut = new FileOutputStream("e://test2.xlsx");
	    wb.write(fileOut);
	    fileOut.close();
	}
	public static void createDateCells()throws Exception{
		Workbook wb = new XSSFWorkbook();
		CreationHelper createHelper=wb.getCreationHelper();
		Sheet sheet=wb.createSheet("new sheet");
		Row row=sheet.createRow((short)1);
		Cell cell=row.createCell(0);
		cell.setCellValue(new Date());
		CellStyle cellStyle=wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
		   cell.setCellValue(new Date());
		    cell.setCellStyle(cellStyle);

		    //you can also set date as java.util.Calendar
		    cell = row.createCell(2);
		    cell.setCellValue(Calendar.getInstance());
		    cell.setCellStyle(cellStyle);
	    FileOutputStream fileOut = new FileOutputStream("e://test2.xlsx");
	    wb.write(fileOut);
	    fileOut.close();
	}
	public static void differentTypeOfCell()throws Exception{
		Workbook wb=new XSSFWorkbook();
		Sheet sheet=wb.createSheet("new sheet");
		Row row=sheet.createRow(2);
		row.createCell(0).setCellValue(1.1);
		row.createCell(1).setCellValue(new Date());
		row.createCell(2).setCellValue(Calendar.getInstance());
		row.createCell(3).setCellValue(true);
		row.createCell(4).setCellValue("hello world");
		row.createCell(5).setCellType(CellType.ERROR);
		//write the output ot a file
		FileOutputStream output=new FileOutputStream("e:"+File.separator+"test.xlsx");
		wb.write(output);
		output.close();
	}
	public static void demonstratesVariousAlignmentOptions()throws Exception{
		Workbook wb=new XSSFWorkbook();
		Sheet sheet=wb.createSheet();
		Row row=sheet.createRow(2);
		row.setHeightInPoints(30);
		createCell(wb, row, (short)0, HorizontalAlignment.CENTER,VerticalAlignment.BOTTOM);
		createCell(wb, row, (short)1, HorizontalAlignment.CENTER_SELECTION,VerticalAlignment.CENTER);
		createCell(wb, row, (short)2, HorizontalAlignment.DISTRIBUTED,VerticalAlignment.DISTRIBUTED);
		createCell(wb, row, (short)3, HorizontalAlignment.FILL,VerticalAlignment.JUSTIFY);
		createCell(wb, row, (short)4, HorizontalAlignment.GENERAL,VerticalAlignment.TOP);
		createCell(wb, row, (short)5, HorizontalAlignment.JUSTIFY,VerticalAlignment.CENTER);
		createCell(wb, row, (short)6, HorizontalAlignment.LEFT,VerticalAlignment.BOTTOM);
		createCell(wb, row, (short)7, HorizontalAlignment.RIGHT,VerticalAlignment.BOTTOM);
		FileOutputStream output=new FileOutputStream("e:"+File.separator+"test.xlsx");
		wb.write(output);
		output.close();
	}
	public static void createCell(Workbook wb,Row row,short column,HorizontalAlignment halign,VerticalAlignment valign){
		Cell cell=row.createCell(column);
		cell.setCellValue("align it");
		CellStyle cellStyle=wb.createCellStyle();
		cellStyle.setAlignment(halign);
		cellStyle.setVerticalAlignment(valign);
		cell.setCellStyle(cellStyle);
	}
	public static void workingWithBoder()throws Exception{
		Workbook wb=new XSSFWorkbook();
		Sheet sheet=wb.createSheet("testSheet");
		sheet.setSelected(true);
		sheet.setZoom(80);
		Header header=sheet.getHeader();
		header.setCenter("center header");
		header.setLeft("left header");
		header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
                HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
		sheet.shiftRows(5, 10, -5);
		Row row=sheet.createRow(1);
		Cell cell=row.createCell(2);
		cell.setCellValue(52);
		row.setHeightInPoints(100);
		CellStyle cellStyle=wb.createCellStyle();
		cellStyle.setBorderBottom(BorderStyle.DASH_DOT);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.DASH_DOT_DOT);
		cellStyle.setLeftBorderColor(IndexedColors.GREEN.getIndex());
		cellStyle.setBorderRight(BorderStyle.DASHED);
		cellStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
		cellStyle.setBorderTop(BorderStyle.MEDIUM_DASHED);
		cellStyle.setTopBorderColor(IndexedColors.RED.getIndex());
		cell.setCellStyle(cellStyle);
		FileOutputStream output=new FileOutputStream("e:"+File.separator+"test.xlsx");
		wb.write(output);
		output.close();
	}
	
	////////////////////////模拟excel假数据
	public static Workbook writeData() throws Exception{
		/*Map<String,Object> result=new HashMap<String,Object>();
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map> data=new ArrayList<>();
		Map<String,Object> dataTemp=new HashMap<String,Object>();
		String []num1={"2","3","4","5","6","7"};
		String []num2={"2","3","4","5","6","7"};
		String []num3={"2","3","4","5","6","7"};
		String []names1={"name","age","address","phone","sex","nation"};
		String []names2={"name","age","address","phone","sex","nation"};
		String []names3={"name","age","address","phone","sex","nation"};
		for (int i = 0; i < 5; i++) {
			dataTemp=new HashMap<String,Object>();
			dataTemp.put("value", num1);
			data.add(dataTemp);
		}
		map.put("title", "china");
		map.put("isOrder", Math.random()>0.5?true:false);
		map.put("columns", names1);
		map.put("data", data);
		result.put("sheet1", map);
		result.put("sheet2", map);
		result.put("sheet3", map);
		return writeData(result);*/
		
		
		String path=Thread.currentThread().getContextClassLoader().getResource("").getPath()+"config"+File.separator+"idCardArea.json";
		File file=new File(path);
		Reader reader=new InputStreamReader(new FileInputStream(file),"utf-8");
		char chars[]=new char[1000];
		int len;
		StringBuilder sb=new StringBuilder();
		while((len=reader.read(chars))!=-1){
			sb.append(new String(chars,0,len));
		}
		JSONObject jsonObject=JSONObject.parseObject(sb.toString());
		JSONObject jsonResult=(JSONObject) jsonObject.get("result");
		List<Map> list1=new ArrayList<>();;
		IdCard idCard=null;
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> map1=new HashMap<String,Object>();
		Map<String,Object> mapTemp1=new HashMap<String,Object>();
		for(String key:jsonResult.keySet()){
			mapTemp1=new HashMap<String,Object>();
			mapTemp1.put("name", key);
			mapTemp1.put("areaName", jsonResult.get(key));
			list1.add(mapTemp1);
		}
		String names1[]={"name","areaName"};
		String key1="sheet1";
		String title1="sheet1的标题";
		map1.put("name", names1);
		map1.put("key", key1);
		map1.put("title", title1);
		map1.put("list", list1);
		
		
		//第二个sheet的数据
		Map<String,Object> map2=new HashMap<String,Object>();
		List<Map> list2=null;
		for(int i=4000;i++<list1.size();){
			list1.remove(i);
		}
		list2=list1;
		String  names2[]={"areaName"};
		String key2="sheet2";
		String title2="sheet2的标题";
		map2.put("name", names2);
		map2.put("key", key2);
		map2.put("title", title2);
		map2.put("list", list2);
		//第三个sheet
		Map<String,Object> map3=new HashMap<String,Object>();
		List<Map> list3=null;
		for(int i=2500;i++<list2.size()-10;){
			list1.remove(i);
		}
		list3=list1;
		int i=0;
		for(Map mapTemp:list3){
			mapTemp.put("id", i+++"");
		}
		String  names3[]={"id","name","areaName"};
		String key3="sheet3";
		String title3="sheet3的标题";
		
		map3.put("name", names3);
		map3.put("key", key3);
		map3.put("title", title3);
		map3.put("list", list3);
		map.put(key1, map1);
		map.put(key2, map2);
		map.put(key3, map3);
		
		
		return writeData(map);
	
		
		
		
		
		
		
	}
	/**
	 * 根据传入的数据写入excel
	 * @param map
	 */
	public static Workbook writeData(Map<String,Object> map)throws Exception{
		Workbook wb=new XSSFWorkbook();
		Sheet sheet=null;
		Map<String,Object> mapTemp=new HashMap<String,Object>();
		//遍历key，获取sheet
		for(String key:map.keySet()){
			sheet=wb.createSheet(key);//创建sheet
			mapTemp=(Map<String, Object>) map.get(key);
			String []columns=(String[]) mapTemp.get("name");//设置列名称
			String title=(String) mapTemp.get("title");//设置大标题
			List<Map> list=(List<Map>) mapTemp.get("list");//每一行的数据
			Row row=null;//创建行
			Cell cell=null;
			Map<String,Object> temp=new HashMap<String,Object>();
			for (int j = 0; j < list.size(); j++) {
				row=sheet.createRow(j);//创建行
				if(j==0){
					for (int i = 0; i < columns.length; i++) {
						cell=row.createCell(i);
						cell.setCellValue(columns[i]);
					}
					continue;
				}
				temp=list.get(j);
				for (int i = 0; i < columns.length; i++) {
					cell=row.createCell(i);
					cell.setCellValue(temp.get(columns[i])+"");
				}	
			}
		}
		return wb;
		/*FileOutputStream out=new FileOutputStream("e:"+File.separator+"test.xlsx");
		wb.write(out);
		out.close();*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

























