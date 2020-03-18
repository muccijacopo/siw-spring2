package it.uniroma3.siw.demospring.services;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.demospring.model.Studente;


@Component 
public class StudenteValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return Studente.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "cognome", "required");
		

	}
	
}
