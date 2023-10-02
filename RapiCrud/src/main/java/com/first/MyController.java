package com.first;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class MyController {
	
   @Autowired
   public Repo repo;
	
	
	/*
	 * @RequestMapping("/first")
	 * 
	 * @ResponseBody public List<Emp> showAll() { return repo.findAll(); }
	 */
	
   //show All Data with the help of GetMapping
   
   @GetMapping("/emp")
    public List<Emp> showAll()
    {
    	return repo.findAll();
    }
   
	/*
	 * @RequestMapping("/first/{id}")
	 * 
	 * @ResponseBody public Emp showById(@PathVariable("id") int id) { return
	 * repo.findById(id).orElse(new Emp()); }
	 */
	
   //show Specific data by id
   
   @GetMapping("/emp/{id}")
   public Emp showById(@PathVariable("id") int id)
   {
	   return repo.findById(id).orElse(new Emp());
   }
   
   
	/*
	 * @RequestMapping("/delete/{id}")
	 * 
	 * @ResponseBody public String deleteById(@PathVariable("id") int id) {
	 * repo.deleteById(id); return "deleted"; }
	 */
	
   // Add data in form of json type annotation @RequestBody resive json type data in and store atomaticly 
   
   @PostMapping("/emp")
   public Emp addEmp(@RequestBody Emp e)
   {
	   return repo.save(e);
   }
	
   // delete data 
   
   @DeleteMapping("/emp/{id}")
   public String deleteEmp(@PathVariable("id") int id)
   {
	   Optional<Emp> e = repo.findById(id);
	   if(e==null || e.isEmpty())
	   {
		   return "not delete Emp";
	   }
	   else 
	   {
		   repo.deleteById(id);
		   return "deleted";
	   }
   }
   
   // update data 
   @PutMapping("/emp/{id}")
   public Emp updateEmp(@RequestBody Emp e,@PathVariable("id") int id)
   {
	   Optional<Emp> e1 = repo.findById(id);
	   if(e1==null || e1.isEmpty())
	   {
		   return new Emp();
	   }
	   else 
	   {
		   e.setId(id);
		   repo.save(e);
		   return e;
	   }
   }

}
