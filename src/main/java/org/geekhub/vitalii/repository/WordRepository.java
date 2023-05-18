package org.geekhub.vitalii.repository;

import org.geekhub.vitalii.dto.StockStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WordRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StockStatsDTO> getStocksStats() {
        String sql =
            "SELECT " +
                "'Cryptocurrency' AS asset_type, " +
                "COUNT(*) AS asset_count, " +
                "AVG(changeInPercent) AS avg_change_percent, " +
                "SUM(marketcap) AS total_marketcap, " +
                "SUM(volume) AS total_volume " +
            "FROM stock " +
            "WHERE type = 1 " +
            "UNION ALL " +
            "SELECT " +
                "'Currency' AS asset_type, " +
                "COUNT(*) AS asset_count, " +
                "AVG(changeInPercent) AS avg_change_percent, " +
                "COALESCE(SUM(marketcap), 0) AS total_marketcap, " +
                "COALESCE(SUM(volume), 0) AS total_volume " +
            "FROM stock " +
            "WHERE type = 2 " +
            "UNION ALL " +
            "SELECT " +
                "'Share' AS asset_type, " +
                "COUNT(*) AS asset_count, " +
                "AVG(changeInPercent) AS avg_change_percent, " +
                "SUM(marketcap) AS total_marketcap, " +
                "SUM(volume) AS total_volume " +
            "FROM stock " +
            "WHERE type = 3;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            StockStatsDTO stockStats = new StockStatsDTO();
            stockStats.setAssetType(rs.getString("asset_type"));
            stockStats.setAssetCount(rs.getLong("asset_count"));
            stockStats.setAvgChangeInPercent(rs.getFloat("avg_change_percent"));
            stockStats.setTotalMarketCap(rs.getLong("total_marketcap"));
            stockStats.setTotalVolume(rs.getLong("total_volume"));
            return stockStats;
        });
    }
}
