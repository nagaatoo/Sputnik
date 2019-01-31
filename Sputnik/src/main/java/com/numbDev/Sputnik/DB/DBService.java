package com.numbDev.Sputnik.DB;

import ch.qos.logback.classic.Logger;
import com.numbDev.Sputnik.Page.MainPage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    private static Logger logger = (Logger) LoggerFactory.getLogger(MainPage.class);

    private final String tableName = "users";
    private Connection con;
    private Statement stmt;
    private ResultSet rs = null;

    @Autowired
    //@Qualifier("sputnik-db")
    private DataSource dataSource;
    // if u want
    private JdbcTemplate template;

    @PostConstruct
    public void test() throws SQLException {
        logger.info("run DBService setting");
        con = dataSource.getConnection();
        stmt = con.createStatement();
        createTable();
        logger.info("setting finish");
    }

    public List<String> getBeepUsers() throws SQLException {
        List<String> users = new ArrayList<>();
        rs = stmt.executeQuery("SELECT name FROM " + tableName);
        while (rs.next()) {
            users.add(rs.getString("name"));
        }
        rs = null;
        return users;
    }

    public boolean addUser(String user) {
        try {
            stmt.executeQuery("INSERT INTO " + tableName + "(name) VALUES ('" + user + "')");
        } catch (SQLException e) {
            logger.error("Can't add user to sputnik base \n" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean removeUser(String user) {
        try {
            stmt.executeQuery("DELETE FROM " + tableName + " WHERE name = '" + user + "'");
        } catch (SQLException e) {
            logger.error("Can't add user to sputnik base \n" + e.getMessage());
            return false;
        }
        return true;
    }

    private void createTable() {
        try {
            stmt.executeQuery("CREATE TABLE IF NOT EXISTS " + tableName + "(name TEXT)");
        } catch (SQLException e) {
            logger.error("Can't create table " + tableName + " to sputnik base \n" + e.getMessage());
        }
    }
}
