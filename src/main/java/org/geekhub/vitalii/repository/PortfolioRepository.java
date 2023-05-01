package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.dto.UserStocksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortfolioRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PortfolioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserStocksDTO> getCustomerStocks(String username) {
        String sql = "SELECT c.username, cc.cryptocurrency_symbol AS symbol, cc.amount AS amount, 'cryptocurrency' AS type " +
            "FROM customer_cryptocurrency AS cc " +
            "JOIN customer AS c ON cc.customer_id = c.id " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT c.username, cs.share_symbol AS symbol, cs.amount AS amount, 'share' AS type " +
            "FROM customer_share AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT c.username, cs.currency_symbol AS symbol, cs.amount AS amount, 'currency' AS type " +
            "FROM customer_currency AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "WHERE c.username = '" + username + "';";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserStocksDTO userStocksDTO = new UserStocksDTO();
            userStocksDTO.setSymbol(rs.getString("symbol"));
            userStocksDTO.setAmount(rs.getBigDecimal("amount"));
            userStocksDTO.setType(rs.getString("type"));
            return userStocksDTO;
        });
    }
}
