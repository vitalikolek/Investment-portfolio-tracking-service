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
            "SELECT cc.cryptocurrency_symbol AS symbol, s.name, cc.amount AS amount, s.price " +
            "FROM customer_cryptocurrency AS cc " +
            "JOIN customer AS c ON cc.customer_id = c.id " +
            "JOIN cryptocurrency AS s on cc.cryptocurrency_symbol = s.symbol " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT cs.share_symbol AS symbol, s.name, cs.amount AS amount, s.price " +
            "FROM customer_share AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "JOIN share s on cs.customer_id = s.id " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT cs.currency_symbol AS symbol, s.name, cs.amount AS amount, s.price " +
            "FROM customer_currency AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "JOIN currency s on s.id = cs.customer_id " +
            "WHERE c.username = '" + username + "';";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ExcelCustomerStockDTO customerStock = new ExcelCustomerStockDTO();
            customerStock.setSymbol(rs.getString("symbol"));
            customerStock.setName(rs.getString("name"));
            customerStock.setAmount(rs.getBigDecimal("amount").doubleValue());
            customerStock.setPrice(rs.getBigDecimal("price").doubleValue());
            return customerStock;
        });
    }
}
