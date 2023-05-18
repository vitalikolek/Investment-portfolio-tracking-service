package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StockDTO> getStockInfo(String type, int limit, int offset) {
        String sql =
            "SELECT * " +
            "FROM stock " +
            "WHERE type IN (SELECT id FROM stock_type WHERE type = ?) " +
            "ORDER BY marketcap DESC " +
            "LIMIT ? OFFSET ?;";

        return jdbcTemplate.query(sql, ps -> {
            ps.setString(1, type);
            ps.setInt(2, limit);
            ps.setInt(3, offset);
        }, (rs, rowNum) -> {
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

    public List<String> getAllStockSymbols() {
        String sql = "SELECT stock.symbol FROM stock;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public List<String> getCryptoSymbols() {
        String sql =
            "SELECT stock.symbol " +
            "FROM stock " +
            "WHERE type = 1";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public List<String> getShareSymbols() {
        String sql =
            "SELECT stock.symbol " +
            "FROM stock " +
            "WHERE type = 3";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public int getStockCount(String type) {
        String sql =
            "SELECT COUNT(id) " +
            "FROM stock " +
            "WHERE type IN (SELECT id FROM stock_type WHERE type = ?);";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, type);
        return count == null ? 0 : count;
    }
}
