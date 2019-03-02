package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Customer;

public interface ICustomerDao extends JpaRepository<Customer, Long>{
	
	public Customer findByMailAndPwd(String mail,String pwd);
	public Customer findByMail (String mail);

}
