/**
 * This interface contains all operations that all types of nodes should support.
 */
public interface Sentence {
  /**
   * Computes and returns the number of words in a sentence.
   *
   * @return the number of words in a sentence
   */
  int getNumberOfWords();

  /**
   * Determines and returns the longest word in a sentence.
   *
   * @return the longest word in a sentence
   */
  String longestWord();

  /**
   * Produces a duplicate of a given sentence.
   *
   * @return a new duplicate list of the given sentence
   */
  Sentence clone();

  /**
   * Merges two sentences into a single sentence.
   *
   * @param other the other sentence which needs to be merged with this one
   * @return a sentence which contains the two former sentences without changing any punctuation
   */
  Sentence merge(Sentence other);

}
