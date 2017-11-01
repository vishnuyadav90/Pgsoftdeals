package com.progresssoft.deals.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;
import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.bean.FileNameDetailsBean;
import com.progresssoft.deals.validator.DealsValidator;

public class DealsUtil {

	@Bean
	DealsValidator dealsValidator() {
		return new DealsValidator();
	};

	public void uploadFile(MultipartFile file, String fileUploadPath) throws IOException {
		byte[] bytes = file.getBytes();
		Path path = Paths.get(fileUploadPath + file.getOriginalFilename());
		Files.write(path, bytes);
	}

	public String getFileUploadPath() {
		Path relativePath = Paths.get("");
		String absoluteFilePath = relativePath.toAbsolutePath().toString();
		String currentPath = absoluteFilePath.replaceAll("\\\\", "/");
		currentPath = currentPath + "/src/main/resources/upload/";
		return currentPath;
	}

	public List<DealDetailsBean> buildBeanFromCsvFile(MultipartFile file, String fileUploadPath)
			throws FileNotFoundException {
		List<DealDetailsBean> beans;
		// to convert csv file to bean object
		beans = new CsvToBeanBuilder(new FileReader(fileUploadPath + file.getOriginalFilename()))
				.withType(DealDetailsBean.class).build().parse();

		// setting file name in list
		beans.forEach(dealDetailsBean -> dealDetailsBean.setFileName(file.getOriginalFilename()));

		//beans.stream().forEach(dealDetailsBean -> System.out.println(dealDetailsBean.toString()));
		return beans;
	}

	public List<DealDetailsBean> getValidDealsList(List<DealDetailsBean> beans) {
		List<DealDetailsBean> successBeans;
		// fetching only valid beans from list
		successBeans = beans.stream().filter(dealDetailsBean -> dealsValidator().checkValidations(dealDetailsBean))
				.collect(Collectors.toList());
		// Below code sniplet is used to convert the date format since it takes more
		// processing time different date formats are currently not allowed

		/*
		 * SimpleDateFormat format = new
		 * java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * successBeans.forEach(dealDetailsBean -> { try { dealDetailsBean
		 * .setDealTimeStamp(format.format(format.parse(dealDetailsBean.getDealTimeStamp
		 * ()))); } catch (ParseException e) {
		 * System.err.println("Error while converting the date format"); } });
		 */

		//successBeans.stream().forEach(dealDetailsBean -> System.out.println(dealDetailsBean.toString()));
		return successBeans;
	}

	public List<FailureDealDetailsBean> getInvalidDealDetails(List<DealDetailsBean> beans) {
		List<FailureDealDetailsBean> failureBeans;
		failureBeans = beans.stream().filter(dealDetailsBean -> !dealsValidator().checkValidations(dealDetailsBean))
				.map(dealDetailsBean -> new FailureDealDetailsBean(dealDetailsBean.getDealId(),
						dealDetailsBean.getFromCurrencyCode(), dealDetailsBean.getFileName(),
						dealDetailsBean.getToCurrencyCode(), dealDetailsBean.getDealTimeStamp(),
						dealDetailsBean.getDealAmount()))
				.collect(Collectors.toList());

		//failureBeans.stream().forEach(dealDetailsBean -> System.out.println(dealDetailsBean.toString()));
		return failureBeans;
	}
	public FileNameDetailsBean getFileDealDetails(String fileName, List<DealDetailsBean> successBeans,
			List<FailureDealDetailsBean> failureBeans, long processingTime) {
		FileNameDetailsBean fileNameDetailsBean = new FileNameDetailsBean();
		fileNameDetailsBean.setFileName(fileName);
		fileNameDetailsBean.setSuccess_Count(successBeans.size());
		fileNameDetailsBean.setFailure_Count(failureBeans.size());
		fileNameDetailsBean.setProcessingTime(processingTime);
		
		return fileNameDetailsBean;
	}
}