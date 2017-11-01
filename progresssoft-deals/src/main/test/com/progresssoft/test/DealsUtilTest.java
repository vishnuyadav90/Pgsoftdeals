package com.progresssoft.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.util.DealsUtil;

@RunWith(SpringRunner.class)
public class DealsUtilTest {
	@Before
	public void setUp(){}

	@After
	public void tearDown(){}

	@Test(expected = FileNotFoundException.class)
	public void uploadFileTest() throws Exception{
		java.io.File ff = new java.io.File("/home/zzsd/test.xlsx");
		FileInputStream fi= new FileInputStream(ff);
		MultipartFile multipartFile = new MockMultipartFile("test.xlsx",fi);
		new DealsUtil().uploadFile(multipartFile,"Z://");
		
	}
	@Test
	public void getFileUploadPathTest() {
		String str = new DealsUtil().getFileUploadPath();
		assertNotEquals("Z:////", str);
		
	}
	@Test(expected = FileNotFoundException.class)
	public void buildBeanFromCsvFileTest() throws Exception{
		java.io.File ff = new java.io.File("/home/zzsd/test.xlsx");
		FileInputStream fi= new FileInputStream(ff);
		MultipartFile multipartFile = new MockMultipartFile("test.xlsx",fi);
		new DealsUtil().buildBeanFromCsvFile(multipartFile,"Z://");
		
	}
	
	@Test
	public void getValidDealsListTest() {
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		List<DealDetailsBean> beanlist2 =new DealsUtil().getValidDealsList(beanlist);
		assertEquals(1, beanlist2.size());
		
	}
	
	@Test
	public void getValidDealsListTest2() {
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-1asas-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		List<DealDetailsBean> beanlist2 =new DealsUtil().getValidDealsList(beanlist);
		assertEquals(0, beanlist2.size());
		
	}
	
	@Test
	public void getInvalidDealsListTest() {
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		List<FailureDealDetailsBean> beanlist2 =new DealsUtil().getInvalidDealDetails(beanlist);
		assertEquals(0, beanlist2.size());
		
	}
	
	@Test
	public void getInvalidDealsListTest2() {
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-1asas-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		List<FailureDealDetailsBean> beanlist2 =new DealsUtil().getInvalidDealDetails(beanlist);
		assertEquals(1, beanlist2.size());
		
	}
		
}
