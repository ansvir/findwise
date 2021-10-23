package com.findwise.service;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.exception.ElementAlreadyIndexedException;
import com.findwise.model.Document;
import com.findwise.util.DocumentParser;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultDocumentSearchEngine implements SearchEngine {

  Set<Document> collection;

  public DefaultDocumentSearchEngine() {
    collection = new HashSet<>();
  }

  @Override
  public void indexDocument(String id, String content) {
    try {
      if (collection
          .stream()
          .anyMatch(it -> it.getId().equals(id))
      ) {
        throw new ElementAlreadyIndexedException(id);
      } else {
        collection.add(new Document(id, DocumentParser.parseContent(content)));
      }
    } catch (ElementAlreadyIndexedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<IndexEntry> search(String term) {
    double tf, idf;

    List<Document> filteredByTermCollection =
        collection
        .stream().map(col -> col.getContent().stream().filter(word -> word.equals(term)).collect(Collectors.toList()))
  }

  private double scoreTFIDF(Document document, String term) {
    int amountOfWords = document.getContent().size();

  }

  public Set<Document> getCollection() {
    return collection;
  }
}
