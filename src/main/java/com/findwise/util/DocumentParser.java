package com.findwise.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.findwise.IndexEntry;
import com.findwise.model.Document;
import com.findwise.model.Word;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DocumentParser {

  private DocumentParser() {}

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

  public static List<Word> parseContent(String content) {
    List<Word> words = new LinkedList<>();
    for (String word: content.split("[\\s\\n]*")) {
      words.add(new Word(word));
    }
    return words;
  }
}
