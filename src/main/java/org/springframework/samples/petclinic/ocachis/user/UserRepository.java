package org.springframework.samples.petclinic.ocachis.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	User findByUsername(String Username);
}
