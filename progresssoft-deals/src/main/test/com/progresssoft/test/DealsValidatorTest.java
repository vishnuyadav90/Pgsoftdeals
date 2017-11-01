package com.progresssoft.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.validator.DealsValidator;


@RunWith(SpringRunner.class)
public class DealsValidatorTest extends AbstracttestClass{
	
	@Before
	public void setUp(){}

	@After
	public void tearDown(){}

	@Test
	public void isAlphaTest(){
		Boolean result = new DealsValidator().isAlpha("vzs");
		assertEquals(true, result);
	}
	@Test
	public void isAlphaTest2(){
		Boolean result = new DealsValidator().isAlpha("v9s");
		assertEquals(false, result);
	}
	@Test
	public void isAlphaTest3(){
		Boolean result = new DealsValidator().isAlpha("@zs");
		assertEquals(false, result);
	}
	@Test
	public void isValidLength(){
		Boolean result = new DealsValidator().isValidLength("@zs");
		assertEquals(true, result);
	}
	@Test
	public void isValidLength2(){
		Boolean result = new DealsValidator().isValidLength("@zsxx");
		assertEquals(false, result);
	}
	@Test
	public void isInt(){
		Boolean result = new DealsValidator().isInt("8756");
		assertEquals(true, result);
	}
	@Test
	public void isInt2(){
		Boolean result = new DealsValidator().isInt("@zsxx");
		assertEquals(false, result);
	}
	
	@Test
	public void isTimeStampValid(){
		Boolean result = new DealsValidator().isTimeStampValid("2017-10-29 11:23:59");
		assertEquals(true, result);
	}
	@Test
	public void isTimeStampValid2(){
		Boolean result = new DealsValidator().isTimeStampValid("2017-10-29 vsd");
		assertEquals(false, result);
	}
	@Test
	public void isTimeStampValid3(){
		Boolean result = new DealsValidator().isTimeStampValid("5454535335");
		assertEquals(false, result);
	}
	@Test
	public void isTimeStampValid4(){
		Boolean result = new DealsValidator().isTimeStampValid("");
		assertEquals(false, result);
	}
	@Test
	public void checkValidationsTest(){
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(true, result);
	}
		@Test
	public void checkValidationsTest2(){
		DealDetailsBean bean = new DealDetailsBean(null, "ASD", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(false, result);
	}
		@Test
	public void checkValidationsTest3(){
		DealDetailsBean bean = new DealDetailsBean("1000", "dfgh", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(false, result);
	}	
	@Test
	public void checkValidationsTest4(){
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-1011-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(true, result);
	}
	@Test
	public void checkValidationsTest5(){
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-10-29 11:23:59", "fgh",
				"INR", "ghsdf.csv");
		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(false, result);
	}	
	@Test
	public void checkValidationsTest6(){
		DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-10-29 11:23:59", "47",
				"asdfg", "ghsdf.csv");
		
		Boolean result = new DealsValidator().checkValidations(bean);
		assertEquals(false, result);
	}
}
