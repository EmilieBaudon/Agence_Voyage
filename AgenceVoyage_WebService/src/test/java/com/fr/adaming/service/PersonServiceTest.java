package com.fr.adaming.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.Service.IPersonService;
import com.fr.adaming.entity.Person;

/**
 * 
 * @author EmilieBaudon
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

	@Autowired
	@Qualifier("PersonService")
	IPersonService service;
	Person person;

	// Test create method

	@Test
	public void a_InsertExistingUser() {
		person = new Person("test", "test", null, "test", "test1@test.com", "test");
		service.create(person);
		person = service.readAll().get(0);
		person = service.create(person);
		assertNull(person);
	}

	@Test
	public void b_InsertUserWithNullId() {
		person = new Person("test", "test", null, "test", "test2@test.com", "test");
		person.setId(null);
		person = service.create(person);
		assertNotNull(person);
	}

	@Test
	public void c_InsertUserWithIdEqualsZero() {
		person = new Person("test", "test", null, "test", "test3@test.com", "test");
		person.setId(0L);
		person = service.create(person);
		assertNotNull(person);
	}

	// Test update method

	@Test
	public void d_UpdateNonExistingUser() {

		person = new Person("test", "test", null, "test", "test4@test.com", "test");
		service.create(person);
		person = service.readAll().get(service.readAll().size() - 1);
		person.setId(person.getId() + 1);
		person = service.update(person);
		assertNull(person);
	}

	@Test
	public void e_UpdateNullId() {
		person = new Person("test", "test", null, "test", "test5@test.com", "test");
		person.setId(null);
		person = service.update(person);
		assertNull(person);
	}

	@Test
	public void f_UpdateIdEqualsZero() {
		person = new Person("test", "test", null, "test", "test6@test.com", "test");
		person.setId(0L);
		person = service.update(person);
		assertNull(person);
	}

	// Test readByMail method

	@Test
	public void g_ReadUnexistingUser() {
		person = new Person("test", "test", null, "test", "test7@test.com", "test");
		service.create(person);
		Person test = service.readByEmail("nonexisting@email.com");
		assertNull(test);
	}

	// Test readById method
	
	@Test
	public void h_ReadUnexistingUser() {
		person = new Person("test", "test", null, "test", "test8@test.com", "test");
		person = service.create(person);
		person = service.readAll().get(service.readAll().size() - 1);
		person.setId(person.getId() + 1);
		person = service.readById(person.getId());
		assertNull(person);
	}

	// Test readAll method
	
	@Test
	public void i_ReadEmptyDB() {
		
		assertNull(service.readAll());
		
	}

	// Test deleteById method

	@Test
	public void j_UnexistingUser() {
		person = new Person("test", "test", null, "test", "test9@test.com", "test");
		person = service.create(person);
		person = service.readAll().get(service.readAll().size() - 1);
		assertTrue(!service.deleteById(person.getId()+1));
	}

	// Test deleteByMail method
	
	@Test
	public void k_UnexistingUser() {
		person = new Person("test", "test", null, "test", "test10@test.com", "test");
		service.create(person);
		assertTrue(!service.deleteByEmail("nonexisting@email.com"));
	}

	// Test Login method
	
	@Test
	public void l_UnexistingUser() {
		person = new Person("test", "test", null, "test", "test11@test.com", "test");
		person = service.create(person);
		Person test = service.Login("nonexisting@email.com", "notExistingPwd");
		assertNull(test);
	}

	@After
	public void suppr() {
		List <Person> people = service.readAll();
		if (people != null) {
			for (int i=0; i<people.size();i++) {
				service.deleteById(people.get(i).getId());
			}
		}
	}

}
