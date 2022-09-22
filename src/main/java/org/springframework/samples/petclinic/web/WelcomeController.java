package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

	    	List<Person> persons= new ArrayList<Person>();
			Person person1=new Person();
			Person person2=new Person();
			Person person3=new Person();
			person1.setFirstName("Manuel");
			person1.setLastName("Barcia");
			persons.add(person1);
			person2.setFirstName("Orlando");
			person2.setLastName("Labrador");
			persons.add(person2);
			person3.setFirstName("Ruben");
			person3.setLastName("Casal");
			persons.add(person3);
			model.put("persons", persons);
			model.put("title", "Project L7-2");
			model.put("group", "L7-2");
			return "welcome";
	  }
}
