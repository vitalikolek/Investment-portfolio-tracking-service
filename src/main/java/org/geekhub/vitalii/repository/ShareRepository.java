package org.geekhub.vitalii.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShareRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getShareSymbols(int limit, int offset) {
        String sql = "SELECT share.symbol " +
                "FROM share " +
                "ORDER BY id " +
                "LIMIT " + limit + " OFFSET " + offset + ";";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }
}
