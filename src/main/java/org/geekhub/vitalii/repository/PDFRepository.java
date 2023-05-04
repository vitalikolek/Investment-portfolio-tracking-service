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
        String sql = "SELECT " +
            "COALESCE(SUM(cc.amount * cr.price), 0) AS cryptocurrency_value," +
            "COALESCE(SUM(cs.amount * s.price), 0) AS share_value," +
            "COALESCE(SUM(cuc.amount * cu.price), 0) AS currency_value," +
            "COALESCE(SUM(cc.amount * cr.price), 0) + COALESCE(SUM(cs.amount * s.price), 0) + " +
                "COALESCE(SUM(cuc.amount * cu.price), 0) AS total_value," +
            "COALESCE(COUNT(DISTINCT cc.cryptocurrency_symbol), 0) + COALESCE(COUNT(DISTINCT cs.share_symbol), 0) + " +
                "COALESCE(COUNT(DISTINCT cuc.currency_symbol), 0) AS stock_count " +
            "FROM customer AS c " +
            "LEFT JOIN customer_cryptocurrency AS cc ON cc.customer_id = c.id " +
            "LEFT JOIN cryptocurrency AS cr ON cr.symbol = cc.cryptocurrency_symbol " +
            "LEFT JOIN customer_share AS cs ON cs.customer_id = c.id " +
            "LEFT JOIN share AS s ON s.symbol = cs.share_symbol " +
            "LEFT JOIN customer_currency AS cuc ON cuc.customer_id = c.id " +
            "LEFT JOIN currency AS cu ON cu.symbol = cuc.currency_symbol " +
            "WHERE " +
            "c.username = '" + username + "';";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            CustomerStatsDTO customerStats = new CustomerStatsDTO();
            customerStats.setCryptocurrencyValue(rs.getLong("cryptocurrency_value"));
            customerStats.setShareValue(rs.getLong("share_value"));
            customerStats.setCurrencyValue(rs.getLong("currency_value"));
            customerStats.setTotalValue(rs.getLong("total_value"));
            customerStats.setStockCount(rs.getInt("stock_count"));
            return customerStats;
        });
    }
}
