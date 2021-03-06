/**
 * This Class keeps a Map storing all the frequencies of each letter present in the initial
 * data-set.
 */
package wazzle.model.maingame.alphabet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** This class is a concrete implementation of {@link WeightedAlphabet} */
public final class WeightedAlphabetImpl implements WeightedAlphabet {

  private Map<Character, Double> weightedLetterMap;

  /**
   * Constructs a new WeightedAlphabetImpl object
   *
   * @param weightedLetterMap a {@code final Map<Character, Double>} object
   */
  public WeightedAlphabetImpl(final Map<Character, Double> weightedLetterMap) {
    Objects.requireNonNull(weightedLetterMap);
    this.weightedLetterMap = new HashMap<>(weightedLetterMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(weightedLetterMap);
  }

  @Override
  public String toString() {
    return "WeightedAlphabetImpl [weightedLetterMap=" + weightedLetterMap + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    WeightedAlphabetImpl other = (WeightedAlphabetImpl) obj;
    return Objects.equals(weightedLetterMap, other.weightedLetterMap);
  }

  /** {@inheritDoc} */
  @Override
  public Map<Character, Double> getWeightedAlphabet() {
    return Collections.unmodifiableMap(weightedLetterMap);
  }
}
