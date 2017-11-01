package com.progresssoft.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.progresssoft.deals.SpringBootWebApplication;
import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.bean.FileNameDetailsBean;
import com.progresssoft.deals.repo.DealsFailureRepository;
import com.progresssoft.deals.repo.DealsRepository;
import com.progresssoft.deals.repo.FileCurrencyCountRepository;
import com.progresssoft.deals.repo.FileNameDetailsRepository;
import com.progresssoft.deals.service.impl.DealsServiceImpl;
import com.progresssoft.deals.util.DealsUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBootWebApplication.class)
@Transactional
public class JPARepoTest extends AbstracttestClass{
	@Autowired
	DealsRepository dealsRepository;

	@Autowired
	DealsFailureRepository dealsFailureRepository;

	@Autowired
	FileCurrencyCountRepository fileCurrencyCountRepository;

	@Autowired
	FileNameDetailsRepository fileNameDetailsRepository;
	
    @Test(expected = NullPointerException.class)
    public void checkByFileNameTest() throws Exception{
    	boolean result = new DealsServiceImpl().checkByFileName("zzqz@");
    	assertEquals(true, false);
    }
    
    @Test
    public void saveValidDealsTest() {
    	
		DealDetailsBean bean = new DealDetailsBean("edasw", "ASD", "2017-10-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		
		boolean result = new DealsServiceImpl().saveValidDeals(beanlist);
		assertEquals(false, result);
    }
    
    @Test
    public void displayDealStatusOnUploadTest() {
		boolean result = new DealsServiceImpl().displayDealStatusOnUpload();
		assertEquals(false, result);
    }
    
    @Test
    public void saveInvalidDealsTest() {
    	
    	DealDetailsBean bean = new DealDetailsBean("1000", "ASD", "2017-1asas-29 11:23:59", "47",
				"INR", "ghsdf.csv");
		List<DealDetailsBean> beanlist = new ArrayList<DealDetailsBean>();
		beanlist.add(bean);
		List<FailureDealDetailsBean> beanlist2 =new DealsUtil().getInvalidDealDetails(beanlist);	
     	boolean result = new DealsServiceImpl().saveInvalidDeals(beanlist2);
		assertEquals(false, result);
    }
    
    @Test
    public void saveFileNameDetailsBeanTest() {    	
    	FileNameDetailsBean bean = new FileNameDetailsBean("testfile", 14, 12, 22);
		boolean result = new DealsServiceImpl().saveFileNameDetailsBean(bean);
		assertEquals(false, result);
    }
}