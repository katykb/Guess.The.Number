package guessNumberGame.models;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Round {
    private int round_id;
    int game_id;
    Timestamp whenPlayed;
    String guess;

    String guessResult;

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Timestamp getWhenPlayed() {
        return whenPlayed;
    }

    public void setWhenPlayed(Timestamp whenPlayed) {
        this.whenPlayed = whenPlayed;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public void setGuessResult(String guessResult) {
        this.guessResult = guessResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return round_id == round.round_id && game_id == round.game_id && Objects.equals(whenPlayed, round.whenPlayed) && Objects.equals(guess, round.guess) && Objects.equals(guessResult, round.guessResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round_id, game_id, whenPlayed, guess, guessResult);
    }
}
