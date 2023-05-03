package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockDTO;
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

    public List<StockDTO> getCryptoInfo(int limit, int offset) {
        String sql = "SELECT * " +
            "FROM cryptocurrency " +
            "ORDER BY marketcap DESC " +
            "LIMIT " + limit + " OFFSET " + offset + ";";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
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

    public List<String> getAllCryptoSymbols() {
        String sql = "SELECT cryptocurrency.symbol FROM cryptocurrency;";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public int cryptocurrenciesCount() {
        String sql = "SELECT COUNT(id) FROM cryptocurrency;";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count == null ? 0 : count;
    }
}
