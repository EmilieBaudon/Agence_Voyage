package com.fr.adaming.restController;

import java.util.List;

import com.fr.adaming.dto.CustomerDto;
import com.fr.adaming.dto.CustomerDtoWithId;
import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.dto.TechnicianDto;
import com.fr.adaming.dto.TechnicianDtoWithId;
import com.fr.adaming.entity.Person;
/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonController {
	
	public String create(CustomerDto dto);
	
	public String register(CustomerDto dto);
	
	public String create(TechnicianDto dto);
	
	public String update(CustomerDtoWithId dto);
	
	public String update(TechnicianDtoWithId dto);
	
	public String updateEmail(CustomerDtoWithId dto);
	
	public String updateEmail(TechnicianDtoWithId dto);
	
	public Person readByEmail(String email);
	
	public Person readById(Long id);
	
	public List<RegisterDto> readAll();
	
	public String deleteById(Long id);
	
	public String deleteByEmail(String Email);
	
	public String Login(LoginDto login);
}
