package fr.eseo.twic.saindrenan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eseo.twic.saindrenan.entity.Customer;
import fr.eseo.twic.saindrenan.dao.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomersByNumber(String number) {
        return customerRepository.findByAccountNo(number);
    }

    public Customer getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    public Customer getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    public Customer getCustomersByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
