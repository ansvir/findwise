package com.findwise.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DocumentParser {

  private DocumentParser() {}

  /**
   * Get document content from resource
   * @param name The full name of the .txt document, including extension
   * @return Content as a string
   */
  public static String getDocumentContentByName(String name) {
    try(InputStream is = Objects.requireNonNull(
        Thread.currentThread().getContextClassLoader().getResourceAsStream(name))
    ) {
      return new String(is.readAllBytes(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Creates list of split words by the space or new line special symbol
   * @param content String that represents content
   * @return Tokens as strings
   */
  public static List<String> tokenizeContent(String content) {
    return new LinkedList<>(Arrays.asList(content.toLowerCase(Locale.ROOT).split("[\\s\\n]+"))
    );
  }
}
