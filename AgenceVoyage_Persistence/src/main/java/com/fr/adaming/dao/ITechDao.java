package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fr.adaming.entity.Technician;

public interface ITechDao extends JpaRepository<Technician, Long> {
	
	public Technician findByMailAndPwd(String mail,String pwd);
	public Technician findByMail (String mail);

}
