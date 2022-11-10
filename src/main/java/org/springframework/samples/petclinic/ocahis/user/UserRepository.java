package org.springframework.samples.petclinic.ocahis.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	User findByUsername(String Username);
}
