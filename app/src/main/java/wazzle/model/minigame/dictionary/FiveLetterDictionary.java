package wazzle.model.minigame.dictionary;

import java.util.Set;
import java.util.stream.Collectors;
import wazzle.model.common.Dictionary;

/**
 * An extension of the Base abstract decorator, filters all the word of length five.
 *
 * @see wazzle.model.minigame.dictionary.FilteredDictionary
 */
public final class FiveLetterDictionary extends FilteredDictionary {

  public FiveLetterDictionary(Dictionary dictionary) {
    super(dictionary);
  }

  @Override
  public Set<String> getWords() {
    return this.filterWords(super.getWords());
  }

  private Set<String> filterWords(final Set<String> words) {
    return words.stream()
        .filter(suitableWords -> suitableWords.length() == 5)
        .collect(Collectors.toSet());
  }
}
