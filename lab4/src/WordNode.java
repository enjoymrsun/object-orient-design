/**
 * This class represents a word node with the given word.
 */
public class WordNode implements Sentence {
  private String content;
  private Sentence rest;

  /**
   * Construct a word node with the given data.
   *
   * @param content the given word stored in this node
   * @param rest    the rest of the sentence follows the current word
   * @throws IllegalArgumentException wrong input type
   */
  public WordNode(String content, Sentence rest) throws IllegalArgumentException {
    if (content.length() == 0 || !isWord(content)) {
      throw new IllegalArgumentException("Wrong Input type for Word!");
    }
    this.content = content;
    this.rest = rest;
  }

  /**
   * Determine whether the given string is a word.
   *
   * @param str the given string
   * @return whether is a word or not
   */
  private boolean isWord(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isLetter(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Get the whole number of words in a sentence.
   *
   * @return the whole number of words in a sentence
   */
  @Override
  public int getNumberOfWords() {
    return 1 + rest.getNumberOfWords();
  }

  /**
   * Get the longest word in a sentence.
   *
   * @return the longest word in a sentence
   */
  @Override
  public String longestWord() {
    String res = rest.longestWord();
    if (res != null) {
      return (content.length() >= res.length()) ? content : res;
    }
    return content;
  }

  /**
   * Get a duplicate of the current sentence.
   *
   * @return a duplicate of the current sentence
   */
  @Override
  public Sentence clone() {
    return new WordNode(content, rest.clone());
  }

  /**
   * Merge two sentences into a single sentence.
   *
   * @param other the other sentence which needs to be merged with this one
   * @return a single sentence which contains the two separate sentences.
   */
  @Override
  public Sentence merge(Sentence other) {
    Sentence res = rest.merge(other);
    return new WordNode(content, res);
  }

  /**
   * Transform the current sentence to a formatted string.
   *
   * @return formatted string which represents the whole sentence
   */
  @Override
  public String toString() {
    String res = rest.toString();
    if (res.length() == 0) {
      return content + ".";
    } else if (rest instanceof PunctuationNode) {
      return content + res;
    } else {
      return content + " " + res;
    }
  }
}
