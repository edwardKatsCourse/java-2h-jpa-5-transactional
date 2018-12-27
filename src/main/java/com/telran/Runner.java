package com.telran;

import com.telran.entity.Customer;
import com.telran.exception.CustomException;
import com.telran.repository.CustomerRepository;
import com.telran.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;


    @Override
    public void run(String... args) throws Exception {
        //Separate transaction for SAVE
        customerService.saveCustomer();

        //Separate transaction for UPDATE
        customerService.updateCustomer();

        System.exit(0);
    }

    private void transactionalExample() {
        //EntityManager

        //begin transaction

        Customer customer1 = Customer.builder()
                .name("David Stone")
                .discountAmount(15)
                .build();

        Customer customer2 = Customer.builder()
                .name("Wendy Doson")
                .discountAmount(20)
                .build();

        //propagation

        //transaction begin
        customerRepository.save(customer1);
        //transaction end


        //transaction begin
        customerRepository.save(customer2);
        //transaction end

        for (int i = 0; i < 1000000_000; i++) {
            //SELECT * FROM CUSTOMER 1000 TIMES
            customerRepository.findById(1L);
//            customerRepository.findById(3L);
        }

        System.out.println("DONE");
        System.exit(0);
        //rollback transaction
//        throw new RuntimeException();

        //commit transaction
    }
}
