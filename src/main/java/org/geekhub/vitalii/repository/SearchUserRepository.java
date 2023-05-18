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
        String sql =
            "SELECT " +
                "c.username," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 1 ), 0) AS crypto," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 2 ), 0) AS currency," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 3 ), 0) AS share " +
            "FROM customer_stock " +
                "JOIN customer c on c.id = customer_stock.customer_id " +
                "JOIN stock s on s.symbol = customer_stock.stock_symbol " +
            "WHERE customer_id IN (SELECT id FROM customer WHERE username = ?) AND role = 'ROLE_USER' " +
            "GROUP BY c.username " +
            "UNION " +
            "SELECT " +
                "c.username," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 1 ), 0) AS crypto," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 2 ), 0) AS currency," +
                "COALESCE (COUNT(DISTINCT s.type) FILTER ( WHERE s.type = 3 ), 0) AS share " +
            "FROM customer_stock " +
                "JOIN customer c on c.id = customer_stock.customer_id " +
                "JOIN stock s on s.symbol = customer_stock.stock_symbol " +
            "WHERE NOT EXISTS(SELECT id FROM customer WHERE username = ?)  AND role = 'ROLE_USER' " +
            "GROUP BY c.username " +
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
