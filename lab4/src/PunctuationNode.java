/**
 * This class represents a punctuation with the given content and the rest of sentence.
 */
public class PunctuationNode implements Sentence {
  private String content;
  private Sentence rest;

  /**
   * Construct a punctuation node by the given data.
   *
   * @param content the given punctuation
   * @param rest    the sentence which follows the current node
   * @throws IllegalArgumentException wrong input type
   */
  public PunctuationNode(String content, Sentence rest) throws IllegalArgumentException {
    if (!isPunctuation(content)) {
      throw new IllegalArgumentException("Wrong Input Type for Punctuation!");
    }

    this.content = content;
    this.rest = rest;
  }

  /**
   * Determine whether the given string is a punctuation.
   *
   * @param content the given string
   * @return whether is a punctuation or not
   */
  private boolean isPunctuation(String content) {
    return content.length() == 1 &&
            !Character.isLetter(content.charAt(0)) &&
            !Character.isDigit(content.charAt(0));
  }

  /**
   * Get the number of the words in a whole sentence.
   *
   * @return the number of the words in a whole sentence
   */
  @Override
  public int getNumberOfWords() {
    return rest.getNumberOfWords();
  }

  /**
   * Get the longest word in a whole sentence.
   *
   * @return the longest word in a whole sentence
   */
  @Override
  public String longestWord() {
    return rest.longestWord();
  }

  /**
   * Get a duplicate of a given sentence.
   *
   * @return a duplicate of a given sentence
   */
  @Override
  public Sentence clone() {
    return new PunctuationNode(content, rest.clone());
  }

  /**
   * Merge two sentences into a a single sentence.
   *
   * @param other the other sentence which needs to be merged with this one
   * @return a single merged sentence
   */
  @Override
  public Sentence merge(Sentence other) {
    Sentence res = rest.merge(other);
    return new PunctuationNode(content, res);
  }

  /**
   * Transform the whole sentence to a formatted string.
   *
   * @return the formatted string which represents a whole sentence
   */
  @Override
  public String toString() {
    if (rest instanceof EmptyNode) {
      return content + rest.toString();
    } else {
      return content + " " + rest.toString();
    }
  }
}
