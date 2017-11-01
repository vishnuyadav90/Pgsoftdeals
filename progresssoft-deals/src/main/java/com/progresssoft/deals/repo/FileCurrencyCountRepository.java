package com.progresssoft.deals.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.progresssoft.deals.bean.FileCurrencyCountDetails;

public interface FileCurrencyCountRepository extends JpaRepository<FileCurrencyCountDetails, String> {

	@Query(nativeQuery = true)
	public List<Object[]>  fetchFileCurrencyCountDetails();
	
}
