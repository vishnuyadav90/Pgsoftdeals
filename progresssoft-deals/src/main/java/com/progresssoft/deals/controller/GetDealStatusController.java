package com.progresssoft.deals.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.progresssoft.deals.bean.FileNameDetailsBean;
import com.progresssoft.deals.service.DealsService;

@Controller
public class GetDealStatusController {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DealsService dealsService;

	@Bean
	FileNameDetailsBean fileNameDetailsBean() {
		return new FileNameDetailsBean();
	};

	@GetMapping("/getDealStatus")
	public String getDealStatus(Model model) {
		logger.info("Entered method getDealStatus in GetDealStatusController class");

		model.addAttribute("fileNameDetailsBean", fileNameDetailsBean());

		logger.info("Exiting method getDealStatus in GetDealStatusController class");
		return "getDealStatus";
	}

	@PostMapping("/getDealStatus")
	public String getDealStatusFromDB(@ModelAttribute FileNameDetailsBean fileNameDetailsBean,
			RedirectAttributes redirectAttributes, Model map, HttpSession session) {
		logger.info("Entered method getDealStatusFromDB in GetDealStatusController class");

		try {
			FileNameDetailsBean fileNameDetailsBeanResult = dealsService.fetchFileNameDetails(fileNameDetailsBean);
			if (null != fileNameDetailsBeanResult)
				map.addAttribute("fileNameDetailsBean", fileNameDetailsBeanResult);
			else {
				session.setAttribute("errordetails",
						"Unable to fetch result for File:" + " " + fileNameDetailsBean.getFileName());

				return "error";
			}
		} catch (Exception e) {
			session.setAttribute("errordetails",
					"Unable to fetch result for File:" + " " + fileNameDetailsBean.getFileName());
			logger.error("Unable to fetch result for File :" + fileNameDetailsBean.getFileName());

			return "error";

		}
		logger.info("Exiting method getDealStatusFromDB in GetDealStatusController class");

		return "dealStatus";
	}

}