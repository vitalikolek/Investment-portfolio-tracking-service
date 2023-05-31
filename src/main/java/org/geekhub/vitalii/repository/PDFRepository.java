package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.CustomerStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PDFRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PDFRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CustomerStatsDTO getCustomerStats(String username) {
        String sql =
            "SELECT " +
            "COALESCE(SUM(cs.amount * s.price) FILTER ( WHERE s.type = 1 ), 0) AS cryptocurrency_value, " +
            "COALESCE(SUM(cs.amount * s.price) FILTER ( WHERE s.type = 2 ), 0) AS currency_value, " +
            "COALESCE(SUM(cs.amount * s.price) FILTER ( WHERE s.type = 3 ), 0) AS share_value, " +
            "COALESCE(SUM(cs.amount * s.price), 0) AS total_value, " +
            "COALESCE(COUNT(cs.stock_symbol), 0) AS stock_count " +
            "FROM customer AS c " +
            "LEFT JOIN customer_stock cs on c.id = cs.customer_id " +
            "LEFT JOIN stock s on s.symbol = cs.stock_symbol " +
            "WHERE " +
            "c.username = ?;";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            CustomerStatsDTO customerStats = new CustomerStatsDTO();
            customerStats.setCryptocurrencyValue(rs.getLong("cryptocurrency_value"));
            customerStats.setShareValue(rs.getLong("currency_value"));
            customerStats.setCurrencyValue(rs.getLong("share_value"));
            customerStats.setTotalValue(rs.getLong("total_value"));
            customerStats.setStockCount(rs.getInt("stock_count"));
            return customerStats;
        }, username);
    }
}
