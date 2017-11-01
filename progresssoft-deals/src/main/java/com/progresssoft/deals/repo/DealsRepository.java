package com.progresssoft.deals.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progresssoft.deals.bean.DealDetailsBean;

public interface DealsRepository extends JpaRepository<DealDetailsBean, String> {

	
}
