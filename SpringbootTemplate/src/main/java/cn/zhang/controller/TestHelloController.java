package cn.zhang.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhang.service.IpAddressService;
import cn.zhang.service.MyCountryService;

@RestController
@RequestMapping("/testHelloController")
public class TestHelloController {

	@Autowired
	IpAddressService ipAddressService;

	@Autowired
	MyCountryService myCountryService;

	@RequestMapping(value = "getCountryCityByIp", method = RequestMethod.GET)
	public String getStr(@RequestParam("ip") String ip) {
		return ipAddressService.getCountryCityByIp(ip);
	}

	@RequestMapping(value = "getGeoIPContext", method = RequestMethod.GET)
	public String getGeoIPContext() {
		return ipAddressService.getGeoIPContext();
	}

	@RequestMapping(value = "getCountrySth", method = RequestMethod.GET)
	public String getCountrySth() {
		String name = "Poland";
		return myCountryService.getCountrySth(name);
	}

	public static void main(String[] args) {
		int year = 1992;
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		c.set(year, 0, 1);
		while (true) {
			if (c.get(Calendar.YEAR) != year) {
				break;
			} else {
				System.out.println(df.format(c.getTime()));
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
			}
		}
	}

}
