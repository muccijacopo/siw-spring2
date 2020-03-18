package it.uniroma3.siw.demospring.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.demospring.model.Studente;
import it.uniroma3.siw.demospring.services.StudenteService;
import it.uniroma3.siw.demospring.services.StudenteValidator;

@Controller
public class StudenteController {
	
	@Autowired
	private StudenteService studenteService;
	
	@Autowired
	private StudenteValidator studenteValidator;
	
	@RequestMapping(value = "/studente", method = RequestMethod.POST)
	public String newStudente(@Valid @ModelAttribute("studente") Studente studente,
			Model model, BindingResult bindingResult) {
		
		this.studenteValidator.validate(studente, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.studenteService.inserisci(studente);
			model.addAttribute("studenti", this.studenteService.tutti());
			return "studenti.html";
		}else {
			return "studenteForm.html";
		}
	}
	
	@RequestMapping(value = "/studente/{id}", method = RequestMethod.GET)
	public String getStudente(@PathVariable ("id") Long id, Model model) {
		if(id!=null) {
			model.addAttribute("studente", this.studenteService.studentePerId(id));
			return "studente.html";
		}else {
			model.addAttribute("studenti", this.studenteService.tutti());
			return "studenti.html";
		}
	}
	
	@RequestMapping("/addStudente")
	public String addStudente(Model model) {
		model.addAttribute("studente", new Studente());
		return "studenteForm.html";
	}
}
