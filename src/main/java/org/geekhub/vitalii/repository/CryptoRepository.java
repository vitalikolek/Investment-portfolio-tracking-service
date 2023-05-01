package org.geekhub.vitalii.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CryptoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CryptoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getCryptoSymbols(int limit, int offset) {
        String sql = "SELECT cryptocurrency.symbol " +
            "FROM cryptocurrency " +
            "ORDER BY id " +
            "LIMIT " + limit + " OFFSET " + offset + ";";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public int cryptocurrenciesCount() {
        String sql = "SELECT COUNT(id) FROM cryptocurrency;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count == null ? 0 : count;
    }
}
