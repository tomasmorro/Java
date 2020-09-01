package com.pgrsoft.springbatchlab.ejemplo02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Job2PersonProcessor implements ItemProcessor<Person,Person> {

	private static final Logger log = LoggerFactory.getLogger(Job2PersonProcessor.class);
	
	@Override
	public Person process(Person person) throws Exception {
		
		Thread.sleep(500);
		
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();
		
		Person newPerson = new Person();
		newPerson.setFirstName(firstName);
		newPerson.setLastName(lastName);
		
		log.info("transformed person: " + newPerson);
		
		return newPerson;
	}

}
