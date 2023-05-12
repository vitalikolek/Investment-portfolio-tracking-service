package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Customer customer) {
        String sql = "INSERT INTO customer (username, password, email, role, creation_time) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getUsername(), customer.getPassword(),
            customer.getEmail(), customer.getRole().toString(), customer.getCreationTime());
    }

    public boolean isCustomerExist(String username, String email) {
        String sql = "SELECT id FROM customer WHERE username = ? OR email = ? LIMIT 1;";
        Integer foundId = jdbcTemplate.queryForObject(sql, Integer.class, username, email);
        return foundId != null;
    }
}
