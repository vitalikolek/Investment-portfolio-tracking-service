package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrencyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CurrencyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getAllCurrencySymbols() {
        String sql = "SELECT currency.symbol FROM currency;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("symbol"));
    }

    public List<CurrencyDTO> getCurrencyInfo() {
        String sql = "SELECT * FROM currency;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CurrencyDTO currencyDTO = new CurrencyDTO();
            currencyDTO.setSymbol(rs.getString( "symbol"));
            currencyDTO.setName(rs.getString("name"));
            currencyDTO.setPrice(rs.getBigDecimal("price"));
            currencyDTO.setDayHigh(rs.getBigDecimal("dayHigh"));
            currencyDTO.setDayLow(rs.getBigDecimal("dayLow"));
            currencyDTO.setChange(rs.getBigDecimal("change"));
            currencyDTO.setChangeInPercent(rs.getBigDecimal("changeInPercent"));
            return currencyDTO;
        });
    }
}
