/**
 * This class represents an empty node.
 */
public class EmptyNode implements Sentence {
  /**
   * Get the number of words in an empty node.
   *
   * @return the number of words in an empty node
   */
  @Override
  public int getNumberOfWords() {
    return 0;
  }

  /**
   * Get the longest word in an empty node.
   *
   * @return the longest word in an empty node
   */
  @Override
  public String longestWord() {
    return null;
  }

  /**
   * Get a duplicate of a given sentence.
   *
   * @return a duplicate of a given sentence
   */
  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  /**
   * Merge two sentences into a single sentence.
   *
   * @param other the other sentence which needs to be merged with this one
   * @return the merged sentence
   */
  @Override
  public Sentence merge(Sentence other) {
    return other.clone();
  }

  /**
   * Transform the empty node to a formatted string.
   *
   * @return a formatted string
   */
  @Override
  public String toString() {
    return "";
  }
}
