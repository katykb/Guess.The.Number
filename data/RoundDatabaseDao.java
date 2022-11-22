package guessNumberGame.data;

import guessNumberGame.models.Game;
import guessNumberGame.models.Round;

import java.sql.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;


@Repository
public class RoundDatabaseDao implements  RoundDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round add(Round round) {

        final String sql = "INSERT INTO round(game_id, whenPlayed, guess, guessResult) VALUES(?,?,?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {

            PreparedStatement statement = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, round.getGame_id());
            statement.setTimestamp(2, round.getWhenPlayed());
            statement.setString(3, round.getGuess());
            statement.setString(4, round.getGuessResult());

            return statement;

        }, keyHolder);

        round.setRound_id(keyHolder.getKey().intValue());

        return round;
    }

    @Override
    public List<Round> getAll() {
        final String sql = "SELECT round_id, game_id, whenPlayed, guess, guessResult FROM round;";
        return jdbcTemplate.query(sql, new RoundMapper());
    }

    @Override
    public List<Round> getAllOfGame(int game_id) {
        final String sql = "SELECT * FROM round WHERE game_id = ? ORDER BY whenPlayed;";

        return jdbcTemplate.query(sql, new RoundMapper(), game_id);
    }
    @Override
    public Round findById(int round_id) {
        final String sql = "SELECT round_id, game_id, whenPlayed, guess, guessResult "
                + "FROM Round WHERE round_id = ? ";
        return jdbcTemplate.queryForObject(sql, new RoundMapper(), round_id);
    }

    @Override public boolean update(Round round) { return false; }

    @Override
    public boolean deleteById(int round_id) {
        final String sql = "DELETE FROM round WHERE round_id = ?;";
        return jdbcTemplate.update(sql, round_id) > 0;
    }
    private static final class RoundMapper implements RowMapper<Round> {
        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRound_id(rs.getInt("round_id"));
            round.setGame_id(rs.getInt("game_id"));
            round.setWhenPlayed(rs.getTimestamp("whenPlayed"));
            round.setGuess(rs.getString("guess"));
            round.setGuessResult(rs.getString("guessResult"));
            return round;
        }
    }
}
