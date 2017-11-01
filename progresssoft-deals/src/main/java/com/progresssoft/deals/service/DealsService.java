package com.progresssoft.deals.service;

import java.util.List;

import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.bean.FileNameDetailsBean;

public interface DealsService {

	boolean checkByFileName(String fileName);
	boolean saveValidDeals(List<DealDetailsBean> successBeans);
	boolean displayDealStatusOnUpload();
	boolean saveInvalidDeals(List<FailureDealDetailsBean> failureBeans);
	boolean saveFileNameDetailsBean(FileNameDetailsBean fileNameDetailsBean);
	FileNameDetailsBean fetchFileNameDetails(FileNameDetailsBean fileNameDetailsBean);

}
