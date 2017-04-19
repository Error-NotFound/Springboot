package cn.zhang.controller;

import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.zhang.excelTest.ExcelTest1;

@RestController
@RequestMapping("/poiController")
public class PoiController {
	
	@RequestMapping(value = "dowload", method = RequestMethod.GET)
	public String dowload(HttpServletResponse res) {
		try {
			Workbook wb=ExcelTest1.writeData();
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-disposition", "attachment;filename="+UUID.randomUUID()+".xlsx");
			OutputStream out=res.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	

}
