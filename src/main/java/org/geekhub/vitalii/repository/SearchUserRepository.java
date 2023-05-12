package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.SearchUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SearchUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SearchUserDTO> findUser(String username) {
        String sql = "SELECT username, " +
            "COALESCE(COUNT(DISTINCT cc.cryptocurrency_symbol), 0) AS crypto, " +
            "COALESCE(COUNT(DISTINCT c.currency_symbol), 0) AS currency, " +
            "COALESCE(COUNT(DISTINCT cs.share_symbol), 0) AS share " +
            "FROM customer " +
                "JOIN customer_cryptocurrency cc ON customer.id = cc.customer_id " +
                "JOIN customer_currency c ON customer.id = c.customer_id " +
                "JOIN customer_share cs ON customer.id = cs.customer_id " +
            "WHERE username ILIKE ? AND role = 'ROLE_USER' " +
            "GROUP BY username " +
            "UNION " +
            "SELECT username, " +
            "COALESCE(COUNT(DISTINCT cc.cryptocurrency_symbol), 0) AS crypto, " +
            "COALESCE(COUNT(DISTINCT c.currency_symbol), 0) AS currency, " +
            "COALESCE(COUNT(DISTINCT cs.share_symbol), 0) AS share " +
            "FROM customer " +
                "JOIN customer_cryptocurrency cc ON customer.id = cc.customer_id " +
                "JOIN customer_currency c ON customer.id = c.customer_id " +
                "JOIN customer_share cs ON customer.id = cs.customer_id " +
            "WHERE NOT EXISTS (SELECT 1 FROM customer WHERE username ILIKE ?) AND role = 'ROLE_USER' " +
            "GROUP BY username " +
            "ORDER BY crypto DESC, currency DESC, share DESC " +
            "LIMIT 10;";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, username);
            ps.setString(2, username);
        },(rs, rowNum) -> {
            SearchUserDTO customer = new SearchUserDTO();
            customer.setUsername(rs.getString( "username"));
            customer.setCrypto(rs.getInt( "crypto"));
            customer.setCurrency(rs.getInt( "currency"));
            customer.setShare(rs.getInt( "share"));
            return customer;
        });
    }
}
