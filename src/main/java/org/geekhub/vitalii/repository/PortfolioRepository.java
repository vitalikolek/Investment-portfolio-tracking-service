package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockInPortfolioDTO;
import org.geekhub.vitalii.model.CustomerRole;
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

    public List<StockInPortfolioDTO> getCustomerStocks(String username) {
        String sql =
            "SELECT cc.cryptocurrency_symbol AS symbol, s.name, cc.amount AS amount, s.price, s.changeinpercent, " +
                "'cryptocurrency' AS type " +
            "FROM customer_cryptocurrency AS cc " +
            "JOIN customer AS c ON cc.customer_id = c.id " +
            "JOIN cryptocurrency AS s on cc.cryptocurrency_symbol = s.symbol " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT cs.share_symbol AS symbol, s.name, cs.amount AS amount, s.price, s.changeinpercent, 'share' AS type " +
            "FROM customer_share AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "JOIN share s on cs.customer_id = s.id " +
            "WHERE c.username = '" + username + "' " +
            "UNION ALL " +
            "SELECT cs.currency_symbol AS symbol, s.name, cs.amount AS amount, s.price, s.changeinpercent, " +
                "'currency' AS type " +
            "FROM customer_currency AS cs " +
            "JOIN customer AS c ON cs.customer_id = c.id " +
            "JOIN currency s on s.id = cs.customer_id " +
            "WHERE c.username = '" + username + "';";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            StockInPortfolioDTO stockInPortfolio = new StockInPortfolioDTO();
            stockInPortfolio.setSymbol(rs.getString("symbol"));
            stockInPortfolio.setName(rs.getString("name"));
            stockInPortfolio.setAmount(rs.getBigDecimal("amount"));
            stockInPortfolio.setPrice(rs.getBigDecimal("price"));
            stockInPortfolio.setChangeInPercent(rs.getBigDecimal("changeInPercent"));
            stockInPortfolio.setType(rs.getString("type"));
            return stockInPortfolio;
        });
    }

    public CustomerRole getCustomerRole(String username) {
        String sql =
            "SELECT customer.role " +
            "FROM customer " +
            "WHERE username = '" + username + "';";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> CustomerRole.valueOf(rs.getString("role")));
    }

    public void deleteStock(String username, String type, String symbol) {
        String sql =
            "DELETE FROM customer_" + type + " " +
            "WHERE " + type + "_symbol = '" + symbol + "' " +
            "AND customer_id IN (SELECT id FROM customer WHERE username = '" + username + "');";
        jdbcTemplate.update(sql);
    }
}
