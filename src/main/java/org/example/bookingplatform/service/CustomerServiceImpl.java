package org.example.bookingplatform.service;

import org.example.bookingplatform.Entity.Customer;
import org.example.bookingplatform.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    return customerRepository.save(existingCustomer);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
