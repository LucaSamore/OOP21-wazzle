package wazzle.model.maingame.letter.score;

import wazzle.model.maingame.alphabet.WeightedAlphabet;

/**
 * This interface provides a method for converting the {@link WeightedAlphabet} into another {@link
 * WeightedAlphabet} which contains the score.
 */
public interface ScoreConverter {

  /**
   * Convert the WeightedAlphabet, which initially contains all the Characters and their frequency
   * (represented by a Double), to an object WeightedAlphabet which contains all the Characters and
   * their score (represented by a Double).
   *
   * @return WeightedAlphabet which contains all the Characters and their score.
   */
  WeightedAlphabet convert();
}
