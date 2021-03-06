package wazzle.model.minigame.attempt;

import com.google.gson.annotations.Expose;
import java.util.Objects;

public final class WordElement {
  @Expose private char character;

  @Expose private int result;

  public WordElement(final char character, Result result) {
    this.character = character;
    this.result = result.getState();
  }

  public char getCharacter() {
    return this.character;
  }

  public int getResult() {
    return this.result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "WordElement [character=" + character + ", result=" + result + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(character, result);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    WordElement other = (WordElement) obj;
    return character == other.character && result == other.result;
  }
}
