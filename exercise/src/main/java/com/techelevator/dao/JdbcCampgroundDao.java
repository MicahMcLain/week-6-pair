package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Campground;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCampgroundDao implements CampgroundDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCampgroundDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Campground getCampgroundById(int id) {
        Campground c = null;
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground " +
                "WHERE campground_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                c = mapRowToCampground(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return c;
    }

    @Override
    public List<Campground> getCampgroundsByParkId(int parkId) {
        List<Campground> c = new ArrayList<>();
        String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
                "FROM campground " +
                "WHERE park_id = ? " +
                "ORDER BY park_id;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
            while (results.next()) {
                c.add(mapRowToCampground(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return c;
    }

    private Campground mapRowToCampground(SqlRowSet results) {
        Campground campground = new Campground();
        campground.setCampgroundId(results.getInt("campground_id"));
        campground.setParkId(results.getInt("park_id"));
        campground.setName(results.getString("name"));
        campground.setOpenFromMonth(results.getInt("open_from_mm"));
        campground.setOpenToMonth(results.getInt("open_to_mm"));
        campground.setDailyFee(results.getDouble("daily_fee"));
        return campground;
    }
}
