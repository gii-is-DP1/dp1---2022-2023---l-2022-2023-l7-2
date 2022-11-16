package org.springframework.samples.petclinic.ocachis.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends  CrudRepository<User, String>{
	

    
}
