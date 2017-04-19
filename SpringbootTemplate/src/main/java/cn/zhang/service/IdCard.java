package cn.zhang.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cn.zhang.util.HttpGetAndPostUtil;
import cn.zhang.util.HttpsConnection;

public class IdCard {
	/**
	 * 验证身份证号的合法性，如果输出的数字和身份证最后一位相等就是正确的
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// String no="372901199210251855";
		// String no="372901199511057212";
		String no = "372901199411247529";

		int year = 1992;
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		c.set(year, 0, 1);
		while (true) {
			String zhouxileiId = "6101231992";
			if (c.get(Calendar.YEAR) != year) {
				break;
			} else {
				zhouxileiId = zhouxileiId + df.format(c.getTime()).substring(5).replace("/", "") + "7266";
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				if ((boolean) checkIdCard(zhouxileiId).get("status") == true
						&& (checkIdCard(zhouxileiId).get("gender") + "").equals("女")) {
					System.out.println(zhouxileiId);
					String url = "https://kyfw.12306.cn/otn/passengers/add";
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("passenger_name", "杨绪广");
					map.put("sex_code", "M");
					map.put("_birthDate", null);
					map.put("mobile_no", null);
					map.put("phone_no", null);
					map.put("email", null);
					map.put("address", null);
					map.put("passenger_type", 1);
					map.put("postalcode", null);
					map.put("studentInfoDTO.school_code", null);
					map.put("studentInfoDTO.department", null);
					map.put("studentInfoDTO.school_class", null);
					map.put("studentInfoDTO.student_no", null);
					map.put("studentInfoDTO.preference_card_no", null);
					map.put("studentInfoDTO.preference_from_station_name", null);
					map.put("studentInfoDTO.preference_from_station_code", null);
					map.put("studentInfoDTO.preference_to_station_name", null);
					map.put("studentInfoDTO.preference_to_station_code", null);
					map.put("country_code", "CN");
					map.put("passenger_id_type_code", "1");
					map.put("passenger_id_no", "372925199412254115");
					map.put("studentInfoDTO.province_code", "11");
					map.put("studentInfoDTO.school_name", "简码/汉字");
					map.put("studentInfoDTO.school_system", "1");
					map.put("studentInfoDTO.enter_year", "2017");
					// HttpGetAndPostUtil.httpPost(url, map);
					try {
					//	HttpsConnection.doPost(url, map, "utf-8", 2000, 2000, false);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				}
			}
		}
	}

	/**
	 * 针对18位身份证号做的验证
	 * 
	 * @param no
	 * @return
	 */
	public static Map<String, Object> checkIdCard(String no) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		int xishu[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		for (int i = 0; i < no.length() - 1; i++) {
			result += Integer.parseInt(no.charAt(i) + "") * xishu[i];
		}
		int mod = result % 11;
		char array2[] = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		map.put("status", array2[mod] == no.charAt(17) ? true : false);// 身份证是否合法
		map.put("gender", no.charAt(16) % 2 == 0 ? "女" : "男");// 身份证的性别
		return map;
	}
}
