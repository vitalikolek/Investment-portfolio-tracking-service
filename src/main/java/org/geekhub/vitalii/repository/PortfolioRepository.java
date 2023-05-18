package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockInPortfolioDTO;
import org.geekhub.vitalii.model.CustomerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
            "SELECT cs.stock_symbol AS symbol, s.name, cs.amount AS amount, s.price, s.changeinpercent, " +
            "'cryptocurrency' AS type " +
            "FROM customer_stock AS cs " +
            "JOIN customer c on cs.customer_id = c.id " +
            "JOIN stock s ON cs.stock_symbol = s.symbol " +
            "WHERE c.username = ?;";

        return jdbcTemplate.query(sql, ps -> ps.setString(1, username), (rs, rowNum) -> {
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
            "WHERE username = ?;";

        return jdbcTemplate.queryForObject(sql,
            (rs, rowNum) -> CustomerRole.valueOf(rs.getString("role")),
            username);
    }
    
    public BigDecimal getBitcoinPrice() {
        String sql = 
            "SELECT price " +
            "FROM stock " +
            "WHERE symbol = 'BTC-USD';";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getBigDecimal("price"));
    }

    public void deleteStock(String username, String symbol) {
        String sql =
            "DELETE FROM customer_stock " +
            "WHERE stock_symbol = ? " +
            "AND customer_id IN (SELECT id FROM customer WHERE username = ?);";

        jdbcTemplate.update(sql, symbol, username);
    }
}
