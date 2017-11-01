package com.progresssoft.deals.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.progresssoft.deals.bean.DealDetailsBean;
import com.progresssoft.deals.bean.FailureDealDetailsBean;
import com.progresssoft.deals.bean.FileNameDetailsBean;
import com.progresssoft.deals.service.DealsService;
import com.progresssoft.deals.util.DealsUtil;
import com.progresssoft.deals.validator.DealsValidator;

@Controller
public class UploadController {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	DealsValidator dealsValidator() {
		return new DealsValidator();
	};

	@Bean
	DealsUtil dealsUtil() {
		return new DealsUtil();
	}

	

	@Autowired
	DealsService dealsService;

	@GetMapping("/")
	public String homePage() {
		logger.info("Entered method homePage in UploadController class"+this.getClass().getSimpleName());
		return "home";
	}

	@GetMapping("/upload")
	public String uploadPage() {
		logger.info("Entered method uploadPage in UploadController class"+this.getClass().getSimpleName());
		return "upload";
	}

	@GetMapping("/error")
	public String errorPage() {
		logger.info("Entered method errorPage in UploadController class"+this.getClass().getSimpleName());
		return "error";
	}

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpSession session, Model map) {
		logger.info("Entered method singleFileUpload in UploadController class"+this.getClass().getSimpleName());
		String fileUploadPath = dealsUtil().getFileUploadPath();
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {
			long startTime = new Date().getTime();
			if (!dealsService.checkByFileName(file.getOriginalFilename())) {
				List<DealDetailsBean> beans;
				List<DealDetailsBean> successBeans;
				List<FailureDealDetailsBean> failureBeans;

				try {

					dealsUtil().uploadFile(file, fileUploadPath);

					beans = dealsUtil().buildBeanFromCsvFile(file, fileUploadPath);

					successBeans = dealsUtil().getValidDealsList(beans);

					if (!dealsService.saveValidDeals(successBeans)) {
						session.setAttribute("errordetails", "Unable to insert the deals details");
						return "error";
					}
					long endTime = new Date().getTime();

					if (!dealsService.displayDealStatusOnUpload()) {
						session.setAttribute("errordetails",
								"Unable to insert the deals details based on currency code");
						return "error";
					}

					failureBeans = dealsUtil().getInvalidDealDetails(beans);

					if (!dealsService.saveInvalidDeals(failureBeans)) {
						session.setAttribute("errordetails", "Unable to insert the invalid deals details");
						return "error";
					}

					long processingTime = (endTime - startTime) / 1000;

					FileNameDetailsBean fileNameDetailsBean = dealsUtil().getFileDealDetails(file.getOriginalFilename(),
							successBeans, failureBeans, processingTime);
					if (!dealsService.saveFileNameDetailsBean(fileNameDetailsBean)) {
						session.setAttribute("errordetails", "Unable to insert the file status details");
						return "error";
					}

					map.addAttribute("fileNameDetailsBean", fileNameDetailsBean);
					session.setAttribute("fileNameDetailsBean", fileNameDetailsBean);
				} catch (IOException e) {
					
					session.setAttribute("errordetails", "Error while uploading file please try again later");
					logger.error("Error while uploading file please try again later"+this.getClass().getSimpleName());
					
					return "error";
				}
			} else {
				return "redirect:/unableToUpload";
			}
		} catch (IllegalStateException e) {
			session.setAttribute("errordetails", "Internal Server error please try again later");
			logger.error("Internal Server error please try again later" + this.getClass().getSimpleName());
			return "error";
		}
		logger.info("Exiting method singleFileUpload in UploadController class"+this.getClass().getSimpleName());
		return "redirect:/uploadStatus";
	}

	@GetMapping("/uploadStatus")
	public String uploadStatus(Model map,HttpSession session) {
		logger.info("Entered method uploadStatus in UploadController class" +this.getClass().getSimpleName());
		map.addAttribute("fileNameDetailsBean", session.getAttribute("fileNameDetailsBean"));
		return "uploadStatus";
	}

	@GetMapping("/unableToUpload")
	public String unableToUpload() {
		logger.info("Entered method uploadStatus in UploadController class"+this.getClass().getSimpleName());
		return "unableToUpload";
	}
}