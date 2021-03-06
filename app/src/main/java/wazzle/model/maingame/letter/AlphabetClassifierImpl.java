package wazzle.model.maingame.letter;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import wazzle.model.maingame.alphabet.WeightedAlphabet;
import wazzle.model.maingame.alphabet.WeightedAlphabetImpl;

/**
 * This class is a concrete implementation for {@link AlphabetClassifier}. It provides methods
 * classify a {@link WeightedAlphabet} in {@link Ranges}.
 */
public final class AlphabetClassifierImpl implements AlphabetClassifier {

  private WeightedAlphabet weightedAlphabet;
  private final Function<EnumMap<Range, Map<Character, Double>>, Map<Range, WeightedAlphabet>> f =
      m ->
          m.entrySet().stream()
              .collect(
                  Collectors.toMap(Map.Entry::getKey, e -> new WeightedAlphabetImpl(e.getValue())));

  /**
   * Construct a new {@link AlphabetClassifier}.
   *
   * @param weightedAlphabet The {@link WeightedAlphabet} which have to be classified.
   */
  public AlphabetClassifierImpl(final WeightedAlphabet weightedAlphabet) {
    this.weightedAlphabet = new WeightedAlphabetImpl(weightedAlphabet.getWeightedAlphabet());
  }

  /** {@inheritDoc} */
  public EnumMap<Range, WeightedAlphabet> classify() {
    EnumMap<Range, Map<Character, Double>> classifiedLetters = new EnumMap<>(Range.class);
    Arrays.asList(Range.values()).stream()
        .collect(Collectors.toSet())
        .forEach(r -> classifiedLetters.put(r, new HashMap<>()));
    List<Entry<Character, Double>> sortedWeightedAlphabet =
        this.sortWeightedAlphabethByValue(
            this.weightedAlphabet.getWeightedAlphabet().entrySet().stream()
                .collect(Collectors.toList()));
    sortedWeightedAlphabet.stream()
        .forEach(
            e ->
                classifiedLetters
                    .get(this.chooseRange(classifiedLetters))
                    .put(e.getKey(), e.getValue()));
    return new EnumMap<>(f.apply(classifiedLetters));
  }

  /**
   * Choose, based on frequency, which range the Character have to be inserted in.
   *
   * @param classifiedLetters
   * @return
   */
  private Range chooseRange(final EnumMap<Range, Map<Character, Double>> classifiedLetters) {
    return classifiedLetters.keySet().stream()
        .filter(k -> !this.isFill(classifiedLetters, k))
        .collect(Collectors.toList())
        .get(0);
  }

  /**
   * Check if the given {@link Range} is fill.
   *
   * @param classifiedLetters The Character, with their frequency which have already been
   *     classified.
   * @param range The {@link Range} which have to be examined.
   * @return boolean which contains the result of the check.
   */
  private boolean isFill(
      final EnumMap<Range, Map<Character, Double>> classifiedLetters, final Range range) {
    return classifiedLetters.get(range).values().stream().reduce((double) 0, Double::sum)
        >= this.computeRangeQuote();
  }

  /**
   * Compute the partition of the entire frequency in odds, based on the number of ranges.
   *
   * @return double which represent the odds for all the ranges.
   */
  private double computeRangeQuote() {
    return this.weightedAlphabet.getWeightedAlphabet().values().stream()
            .reduce((double) 0, Double::sum)
        / Range.values().length;
  }

  /**
   * Sort the {@link WeightedAlphabet} based on the frequency in order to insert the Character with
   * the highest frequency in the highest range and so on.
   *
   * @param weightedAlphabetList The {@link WeightedAlphabet} as List which have to be ordered.
   * @return List<Entry>> which represent the {@link WeightedAlphabet} as List ordered descending
   */
  private List<Entry<Character, Double>> sortWeightedAlphabethByValue(
      final List<Map.Entry<Character, Double>> weightedAlphabetList) {
    Collections.sort(weightedAlphabetList, ((v1, v2) -> v2.getValue().compareTo(v1.getValue())));
    return weightedAlphabetList;
  }
}
