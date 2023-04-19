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
	Optional<supplier> getApplication(@PathVariable("id") Long id) { 
		return repo.findById(id); }

	@RequestMapping(value = "/supplierbycode/{item_code}",method = RequestMethod.GET) 
	 // @GetMapping("/supplier/{item_code}") List<supplier>
	  //List<supplier> getApplication(@PathVariable("item_code") String item_code)
	//{ return  repo.findByItemCode(item_code);}
	public List<supplier> getApplication(@PathVariable(value="item_code") String item_code) 
	{
		System.out.println(item_code);
		
	    return repo.findByItemCode(item_code);
	}
	@RequestMapping(value = "/suppliername/{supplier_name}",method = RequestMethod.GET) 
	 // @GetMapping("/supplier/{item_code}") List<supplier>
	  //List<supplier> getApplication(@PathVariable("item_code") String item_code)
	//{ return  repo.findByItemCode(item_code);}
	public List<supplier> getApplication1(@PathVariable(value="supplier_name") String supplier_name) 
	{
		System.out.println(supplier_name);
		
	    return repo.findBySupplier(supplier_name);
	}
	 
	@GetMapping(value = "/items/{item}") 
	public List<supplier> getApplication2(@PathVariable(value="item") String item) 
	{
		System.out.println(item);
		
		return repo.findByItem(item);
	}
		 
	
	@RequestMapping(value = "/supplier", method = RequestMethod.POST)
	public supplier create(@RequestBody supplier supplier) {
		return repo.save(supplier);
	}
	
	
	@DeleteMapping(value="/supplier/{id}")
	ResponseEntity<supplier> deleteApplication(@PathVariable("id") Long id) {

		// First fetch an existing Application and then delete it. 
		Optional<supplier> optionalApplication = repo.findById(id); 
		supplier existingApplication=optionalApplication.get();

		// Return the deleted wine 
		repo.delete(existingApplication);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;		
	}

	@PutMapping(value="/supplier/{id}")
	ResponseEntity<supplier> updateApplication(@PathVariable("id") Long id, @RequestBody supplier application) {

		// First fetch an existing wine and then modify it. 
		Optional<supplier> optionalSupplier = repo.findById(id); 
		supplier existingSupplier = optionalSupplier.get();

		// Now update it back 
		existingSupplier.setId(application.getId());
		existingSupplier.setSupplier_name(application.getSupplier_name());
		existingSupplier.setItem(application.getItem());
		existingSupplier.setItem_code(application.getItem_code());
		existingSupplier.setManufacting_cost(application.getManufacting_cost());
		existingSupplier.setSelling_cost(application.getSelling_cost());
		existingSupplier.setProfit_gained(application.getProfit_gained());

		supplier savedApplication = repo.save(existingSupplier) ;
		// Return the updated product  
		return new ResponseEntity<>(savedApplication, HttpStatus.OK) ;		
	}
}
