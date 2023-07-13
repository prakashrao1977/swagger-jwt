package com.test.microservice.controller;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.test.microservice.data.Customer;

@RestController
@RequestMapping("/customer")
public class TestMicroserviceController {
    Map<String, Customer> customerMap = new ConcurrentHashMap<String, Customer>();
    
    @GetMapping("/{id}")
    public Customer getCustomer (@PathVariable String id)
    {
        System.out.println("Getting Customer for id "+id);
        return customerMap.get(id);
    }
    
    @GetMapping("/")
    public Collection<Customer> getCustomers ()
    {
        System.out.println("Getting Customers");
        return customerMap.values();
    }    

    @PostMapping("/add")
    public void add (@RequestBody Customer customer)
    {
        System.out.println("Adding Customer "+customer);
        customerMap.put(customer.getId(), customer);
    }
}
