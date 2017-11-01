package com.progresssoft.deals.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.progresssoft.deals.bean.DealDetailsBean;

public class DealsValidator {

	public boolean checkValidations(DealDetailsBean dealDetailsBean) {
		return Optional.ofNullable(dealDetailsBean.getDealId()).isPresent()
				&& Optional.ofNullable(dealDetailsBean.getFromCurrencyCode()).isPresent()
				&& isAlpha(dealDetailsBean.getFromCurrencyCode())
				&& isValidLength(dealDetailsBean.getFromCurrencyCode())
				&& Optional.ofNullable(dealDetailsBean.getToCurrencyCode()).isPresent()
				&& isAlpha(dealDetailsBean.getToCurrencyCode()) && isValidLength(dealDetailsBean.getToCurrencyCode())
				&& Optional.ofNullable(dealDetailsBean.getDealAmount()).isPresent()
				&& isInt(dealDetailsBean.getDealAmount()) && isTimeStampValid(dealDetailsBean.getDealTimeStamp());
	}

	public boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}

	public boolean isValidLength(String name) {
		return name.length() == 3;
	}

	public boolean isInt(String name) {
		return name.matches("[0-9]+");
	}

	public static boolean isTimeStampValid(String inputString) {
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			format.parse(inputString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}