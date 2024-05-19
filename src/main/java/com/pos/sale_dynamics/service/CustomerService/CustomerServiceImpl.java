package com.pos.sale_dynamics.service.CustomerService;

import com.pos.sale_dynamics.domain.Customer;
import com.pos.sale_dynamics.dto.CustomerDTO;
import com.pos.sale_dynamics.mapper.CustomerDTOMapper;
import com.pos.sale_dynamics.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDTOMapper customerDTOMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public ResponseEntity<CustomerDTO> create(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(customerDTOMapper.mapToCustomer(customerDTO));
        return new ResponseEntity<>(customerDTOMapper.apply(customer), HttpStatus.OK);
    }

    @Override
    public List<CustomerDTO> findByKeyword(String infix) {
        return customerRepository.findByNameContaining(infix).stream().map(customer -> customerDTOMapper.apply(customer)).toList();
    }

    @Override
    public ResponseEntity<CustomerDTO> findByPhone(String phone) {
        Optional<Customer> customerRecord = customerRepository.findByPhone(phone);
        return customerRecord.map(customer -> new ResponseEntity<>(customerDTOMapper.apply(customer), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
