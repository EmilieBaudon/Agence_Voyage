package com.fr.adaming.restController;

import java.util.List;

import com.fr.adaming.dto.LoginDto;
import com.fr.adaming.dto.RegisterDto;
import com.fr.adaming.entity.Person;
/**
 * 
 * @author EmilieBaudon
 *
 */
public interface IPersonController {
	
	public String create(RegisterDto dto);
	
	public String update(RegisterDto dto);
	
	public Person readByEmail(String email);
	
	public Person readById(Long id);
	
	public List<RegisterDto> readAll();
	
	public String deleteById(Long id);
	
	public String deleteByEmail(String Email);
	
	public String Login(LoginDto login);
}
