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

    public void addStock(String username, UserStockDTO stock) {
        String sql =
            "INSERT INTO customer_stock (customer_id, stock_symbol, amount) " +
            "VALUES " +
                "((SELECT id " +
                "FROM customer " +
                "WHERE username = ?), ?, ?) " +
            "ON CONFLICT (customer_id, stock_symbol) " +
            "DO UPDATE SET amount = ?;";

        jdbcTemplate.update(sql, username, stock.getSymbol(), stock.getAmount(), stock.getAmount());
    }
}
