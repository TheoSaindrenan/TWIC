package fr.eseo.twic.saindrenan.controller;

import fr.eseo.twic.saindrenan.entity.Customer;
import fr.eseo.twic.saindrenan.service.CustomerService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/account_number/{number}")
    public ResponseEntity<Customer> getCustomersByNumber(@PathVariable String number) {
        Customer customer = customerService.getCustomersByNumber(number);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/first_name/{f_name}")
    public ResponseEntity<Customer> getCustomersByFirstName(@PathVariable String f_name) {
        Customer customer = customerService.getCustomersByFirstName(f_name);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/last_name/{l_name}")
    public ResponseEntity<Customer> getCustomersByLastName(@PathVariable String l_name) {
        Customer customer = customerService.getCustomersByLastName(l_name);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomersByEmail(@PathVariable String email) {
        Customer customer = customerService.getCustomersByEmail(email);
        if (customer != null) {
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);
        }
    }


}
