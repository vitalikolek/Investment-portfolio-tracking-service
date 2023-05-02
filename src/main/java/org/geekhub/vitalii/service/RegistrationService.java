package org.geekhub.vitalii.service;

import org.geekhub.vitalii.model.Customer;
import org.geekhub.vitalii.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, PasswordEncoder passwordEncoder) {
        this.registrationRepository = registrationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer.generateCreationTime();
        registrationRepository.save(customer);
    }
}
