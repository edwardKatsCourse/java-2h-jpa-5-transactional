package com.telran.service;

import com.telran.entity.Customer;
import com.telran.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public void saveCustomer() {
        //Transient - new NOT-SAVED entity
        Customer customer1 = new Customer("David Stone", 15);


        customer1 = customerRepository.save(customer1);
        //Managed - hibernate looks for this object's changes


//        updateCustomer();
//        customerService.updateCustomer();

    }

    @Transactional
    public void updateCustomer() {
        //To managed
        Customer customer1 = customerRepository.findById(1L).get();

        customer1.setDiscountAmount(99);
        customer1.setName("Jessica Watson");
    }
}
