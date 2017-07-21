package de.tarent.javastarter.springboot.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@DependsOn("liquibase")
public class GreetingRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GreetingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.initialize();
    }

    private void initialize() {
        jdbcTemplate.execute("INSERT INTO greetings (id, greeting) VALUES (1, 'Hallo Welt')");
        jdbcTemplate.execute("INSERT INTO greetings (id, greeting) VALUES (2, 'Hello World')");
    }

    List<Greeting> getAll() {
        return jdbcTemplate.query("SELECT * FROM greetings", new GreetingsRowMapper());
    }

    Greeting get(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM greetings g WHERE g.id = ?", new GreetingsRowMapper(), id);
    }

    public static class GreetingsRowMapper implements RowMapper<Greeting> {
        @Override
        public Greeting mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Greeting(
                rs.getLong(1),
                rs.getString(2)
            );
        }
    }


}
