package com.progresssoft.deals.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.progresssoft.deals.bean.FailureDealDetailsBean;

public interface DealsFailureRepository extends JpaRepository<FailureDealDetailsBean, String> {

	
}
