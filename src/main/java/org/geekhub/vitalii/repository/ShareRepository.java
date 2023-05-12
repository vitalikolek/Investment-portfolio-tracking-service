package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockDTO;
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

    public List<StockDTO> getShareInfo(int limit, int offset) {
        String sql =
            "SELECT * " +
            "FROM share " +
            "ORDER BY marketcap DESC " +
            "LIMIT ? OFFSET ?;";

        return jdbcTemplate.query(sql, ps -> {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
        },(rs, rowNum) -> {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setSymbol(rs.getString( "symbol"));
            stockDTO.setName(rs.getString("name"));
            stockDTO.setPrice(rs.getBigDecimal("price"));
            stockDTO.setDayHigh(rs.getBigDecimal("dayHigh"));
            stockDTO.setDayLow(rs.getBigDecimal("dayLow"));
            stockDTO.setChange(rs.getBigDecimal("change"));
            stockDTO.setChangeInPercent(rs.getBigDecimal("changeInPercent"));
            stockDTO.setMarketCap(rs.getBigDecimal("marketCap"));
            stockDTO.setVolume(rs.getLong("volume"));
            return stockDTO;
        });
    }

    public List<String> getAllShareSymbols() {
        String sql = "SELECT share.symbol FROM share;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public int sharesCount() {
        String sql = "SELECT COUNT(id) FROM share;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count == null ? 0 : count;
    }
}
