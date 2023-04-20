package ait.student.nutrition.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ait.student.nutrition.nutrition.dto.supplier;
import ait.student.nutrition.rep.NutritionRepository;

@RestController
@Service
@RequestMapping("/")

public class NutritionController {
	@Autowired
	NutritionRepository repo;
	@GetMapping(value="/getsuppliers")
	List<supplier> getApplicationForCategory() 
	{
		return repo.findAll(Sort.by("item").descending());
	}
	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
	public 
	Optional<supplier> getApplication(@PathVariable("id") Long id) 
	{ 
		return repo.findById(id); 
	}

	@RequestMapping(value = "/supplierbycode/{item_code}",method = RequestMethod.GET) 
	public List<supplier> getApplication(@PathVariable(value="item_code") String item_code) 
	{
		
		
	    return repo.findByItemCode(item_code);
	}
	@RequestMapping(value = "/suppliername/{supplier_name}",method = RequestMethod.GET) 
	public List<supplier> getApplication1(@PathVariable(value="supplier_name") String supplier_name) 
	{
		
		
	    return repo.findBySupplier(supplier_name);
	}
	 
	@GetMapping(value = "/items/{item}") 
	public List<supplier> getApplication2(@PathVariable(value="item") String item) 
	{		
		return repo.findByItem(item);
	}
		 
	
	@RequestMapping(value = "/supplier", method = RequestMethod.POST)
	public supplier create(@RequestBody supplier supplierpost) {
		return repo.save(supplierpost);
	}
	
	
	
}
