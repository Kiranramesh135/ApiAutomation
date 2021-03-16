package com.kiran.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
//		// TODO Auto-generated method stub
//
//		IResultMap pass = context.getPassedTests();
//
//		int passNo = pass.size();
//
//		String html = "";
//
//
//		try {
//			FileReader fr = new FileReader(new File("config/test.html"));
////			fr.read(target);
////			FileInputStream fis = new FileInputStream(new File("config/test.html"));
////			fis.
//		}
//		catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//
//
//		Document a;
//		try {
//			a = Jsoup.parse(new File(System.getProperty("user.dir")+"/config/test.html"), "UTF-8");
//			a.appendText("dada");
//
//			System.out.println("============+++++++");
//			Elements b = a.getElementsByTag("h1");
//
//			System.out.println(b.text());
//			System.out.println();
//			System.out.println("===============================================");
//		}
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//
	}

}
