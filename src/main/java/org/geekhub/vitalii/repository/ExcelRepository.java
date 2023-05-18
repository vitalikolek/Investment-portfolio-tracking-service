package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.ExcelCustomerStockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExcelRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExcelRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ExcelCustomerStockDTO> getCustomerStocks(String username) {
        String sql =
            "SELECT s.symbol, s.name, cc.amount, s.price " +
            "FROM customer_stock AS cc " +
            "JOIN customer AS c ON cc.customer_id = c.id " +
            "JOIN stock s on s.symbol = cc.stock_symbol " +
            "WHERE c.username = ? AND amount * s.price > 10;";

        return jdbcTemplate.query(sql, ps -> ps.setString(1, username), (rs, rowNum) -> {
            ExcelCustomerStockDTO customerStock = new ExcelCustomerStockDTO();
            customerStock.setSymbol(rs.getString("symbol"));
            customerStock.setName(rs.getString("name"));
            customerStock.setAmount(rs.getBigDecimal("amount").doubleValue());
            customerStock.setPrice(rs.getBigDecimal("price").doubleValue());
            return customerStock;
        });
    }
}
