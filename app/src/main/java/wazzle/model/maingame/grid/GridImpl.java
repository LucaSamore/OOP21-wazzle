package wazzle.model.maingame.grid;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import wazzle.model.maingame.letter.Letter;

/** This class is a concrete implementation of {@link Grid} */
public final class GridImpl implements Grid {
  private final Set<Letter> letters;
  private final Set<String> wordsCanBeFound;

  /**
   * Construct a new GridImpl object
   *
   * @param letters a {@code Set<Letter>}
   * @param wordsCanBeFound a {@code Set<String>}
   * @see wazzle.model.maingame.letter.Letter
   */
  public GridImpl(final Set<Letter> letters, final Set<String> wordsCanBeFound) {
    Objects.requireNonNull(letters);
    Objects.requireNonNull(wordsCanBeFound);
    this.letters = new HashSet<>(letters);
    this.wordsCanBeFound = new HashSet<>(wordsCanBeFound);
  }

  /** {@inheritDoc} */
  @Override
  public Set<Letter> getLetters() {
    return Set.copyOf(this.letters);
  }

  /** {@inheritDoc} */
  @Override
  public Set<String> getWordsCanBeFound() {
    return Set.copyOf(this.wordsCanBeFound);
  }

  /** {@inheritDoc} */
  @Override
  public int getTotalScore() {
    return this.letters.stream().map(Letter::getScore).reduce(0, (x, y) -> x + y);
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode() {
    return Objects.hash(letters, wordsCanBeFound);
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    GridImpl other = (GridImpl) obj;
    return Objects.equals(letters, other.letters)
        && Objects.equals(wordsCanBeFound, other.wordsCanBeFound);
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Here's a lo<3ly grid "
        + System.lineSeparator()
        + "Letters inside the grid: "
        + System.lineSeparator()
        + this.letters
        + System.lineSeparator()
        + System.lineSeparator()
        + String.format("Words you can find inside this grid [%d]: ", this.wordsCanBeFound.size())
        + System.lineSeparator()
        + this.wordsCanBeFound
        + System.lineSeparator();
  }
}
