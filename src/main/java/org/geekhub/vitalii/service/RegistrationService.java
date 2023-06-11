package org.geekhub.vitalii.service;

import org.geekhub.vitalii.entity.Customer;
import org.geekhub.vitalii.entity.Role;
import org.geekhub.vitalii.repository.CustomerRepository;
import org.geekhub.vitalii.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistrationService {

    private final CustomerRepository registrationRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(CustomerRepository registrationRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.registrationRepository = registrationRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer.setCreationTime(new Date());
        customer.setRole(convertRole(customer.getRole().getId()));
        registrationRepository.save(customer);
    }

    public Role convertRole(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    public boolean isCustomerExist(String username, String email) {
        Example<Customer> usernameExample = Example.of(new Customer(null, username, null, null, null, null));
        boolean isUsernameExist = registrationRepository.findBy(usernameExample, FluentQuery.FetchableFluentQuery::first).isPresent();

        Example<Customer> emailExample = Example.of(new Customer(null, null, email, null, null, null));
        boolean isEmailExist = registrationRepository.findBy(emailExample, FluentQuery.FetchableFluentQuery::first).isPresent();

        return isUsernameExist || isEmailExist;
    }
}
