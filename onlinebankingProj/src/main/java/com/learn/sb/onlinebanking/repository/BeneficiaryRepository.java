package com.learn.sb.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learn.sb.onlinebanking.entity.BeneficiaryUser;

@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryUser, Long>{
	
	BeneficiaryUser findByAccountNumber(Long accountNumber);

}
