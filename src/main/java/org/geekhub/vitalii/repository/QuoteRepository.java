package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.UserStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QuoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addStock(String username , UserStockDTO stock) {
        String sql =
            "INSERT INTO customer_" + stock.getType() + " (customer_id, " + stock.getType() + "_symbol, amount) " +
            "VALUES " +
                "((SELECT id " +
                "FROM customer " +
                "WHERE username = '" + username + "'), '" + stock.getSymbol() + "', " + stock.getAmount() + ")" +
            "ON CONFLICT (customer_id, " + stock.getType() + "_symbol) " +
            "DO UPDATE SET amount = " + stock.getAmount() + ";";

        jdbcTemplate.update(sql);
    }
}
