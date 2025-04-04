package edu.moravian.DataStorage;
public record Score(String word, int score) {
    public Score {
        if (score < 0) {
            throw new IllegalArgumentException("Score must be non-negative");
        }
    }
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return String.format("%s: %d", word, score);
    }
}
