package com.progresssoft.deals.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.bean.FileCurrencyCountDetails;
import com.progresssoft.deals.bean.FileNameDetailsBean;
import com.progresssoft.deals.repo.DealsFailureRepository;
import com.progresssoft.deals.repo.DealsRepository;
import com.progresssoft.deals.repo.FileCurrencyCountRepository;
import com.progresssoft.deals.repo.FileNameDetailsRepository;
import com.progresssoft.deals.service.DealsService;
import com.progresssoft.deals.validator.DealsValidator;

@Service
public class DealsServiceImpl implements DealsService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	DealsValidator dealsValidator() {
		return new DealsValidator();
	};

	@Autowired
	DealsRepository dealsRepository;

	@Autowired
	DealsFailureRepository dealsFailureRepository;

	@Autowired
	FileCurrencyCountRepository fileCurrencyCountRepository;

	@Autowired
	FileNameDetailsRepository fileNameDetailsRepository;

	@Override
	public boolean checkByFileName(String fileName) {

		logger.info("Entered method checkByFileName in DealsServiceImpl class");

		return Optional.ofNullable(fileNameDetailsRepository.exists(fileName)).get();
	}

	@Override
	public boolean saveValidDeals(List<DealDetailsBean> successBeans) {

		logger.info("Entered method saveValidDeals in DealsServiceImpl class");

		boolean insertStatus = true;
		try {
			dealsRepository.save(successBeans);
		} catch (Exception e) {
			insertStatus = false;
			logger.error(e.getMessage() + this.getClass().getSimpleName());
		}

		logger.info("Exiting method saveValidDeals in DealsServiceImpl class");

		return insertStatus;
	}

	@Override
	public boolean displayDealStatusOnUpload() {

		logger.info("Entered method displayDealStatusOnUpload in DealsServiceImpl class");

		boolean insertStatus = true;
		try {
			List<Object[]> fileCurrencyTempList = fileCurrencyCountRepository.fetchFileCurrencyCountDetails();
			List<FileCurrencyCountDetails> fileCurrencyCountDetailsList;
			fileCurrencyCountDetailsList = (List<FileCurrencyCountDetails>) fileCurrencyTempList.stream()
					.map(userDetails -> new FileCurrencyCountDetails((String) userDetails[0],
							((BigInteger) userDetails[1]).longValue()))
					.collect(Collectors.toList());

			fileCurrencyCountRepository.deleteAll();
			//fileCurrencyCountDetailsList.stream().forEach(dealDetailsBean -> System.out.println(dealDetailsBean.toString()));

			fileCurrencyCountRepository.save(fileCurrencyCountDetailsList);
		} catch (Exception e) {
			insertStatus = false;
			logger.error(e.getMessage() + this.getClass().getSimpleName());
		}

		logger.info("Exiting method displayDealStatusOnUpload in DealsServiceImpl class");

		return insertStatus;
	}

	@Override
	public boolean saveInvalidDeals(List<FailureDealDetailsBean> failureBeans) {

		logger.info("Entered method saveInvalidDeals in DealsServiceImpl class");

		boolean insertStatus = true;

		try {
			dealsFailureRepository.save(failureBeans);
		} catch (Exception e) {
			insertStatus = false;
			logger.error(e.getMessage() + this.getClass().getSimpleName());
		}

		logger.info("Exiting method saveInvalidDeals in DealsServiceImpl class");

		return insertStatus;
	}

	@Override
	public boolean saveFileNameDetailsBean(FileNameDetailsBean fileNameDetailsBean) {

		logger.info("Entered method saveFileNameDetailsBean in DealsServiceImpl class");

		boolean insertStatus = true;

		try {
			fileNameDetailsRepository.save(fileNameDetailsBean);
		} catch (Exception e) {
			insertStatus = false;
			logger.error(e.getMessage() + this.getClass().getSimpleName());
		}

		logger.info("Exiting method saveFileNameDetailsBean in DealsServiceImpl class");

		return insertStatus;
	}

	public FileNameDetailsBean fetchFileNameDetails(FileNameDetailsBean fileNameDetailsBean) {
		logger.info("Entering method fetchFileNameDetails in DealsServiceImpl class");
		return fileNameDetailsRepository.findOne(fileNameDetailsBean.getFileName());

	}
}
