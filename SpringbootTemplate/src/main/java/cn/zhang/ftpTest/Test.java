package cn.zhang.ftpTest;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import com.ibm.wsdl.util.IOUtils;

public class Test {
	public static void main(String[] args) {
		FtpTest test=new FtpTest();
		test.downFile("192.168.1.29", 21, "liu", "liu", "D:\\ftpTest", "test.json", "D:\\log");
	}
}
