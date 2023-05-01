package org.geekhub.vitalii.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getCurrencySymbols() {
        String sql = "SELECT currency.symbol " +
                "FROM currency " +
                "ORDER BY id;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }
}
