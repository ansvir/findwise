package com.findwise.service;

import static java.lang.Math.log10;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.findwise.exception.ElementAlreadyIndexedException;
import com.findwise.exception.ElementNotIndexedException;
import com.findwise.model.Document;
import com.findwise.util.DocumentParser;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (!collection.add(new Document(id, DocumentParser.tokenizeContent(content)))) {
          throw new ElementNotIndexedException();
        }
      }
    } catch (ElementAlreadyIndexedException | ElementNotIndexedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<IndexEntry> search(String term) {
    return filterByTerm(
        collection
            .stream()
            .peek(doc -> doc.setScore(0.0))
            .collect(Collectors.toSet()),
        term
    )
        .peek(doc -> scoreTFIDF(doc, term))
        .collect(Collectors.toList());
  }

  /**
   * Sort descending by TF-IDF
   *
   * @return Sorted list
   */
  public List<Document> sortByTFIDF(String term) {
    Set<Document> sortedCollection = new LinkedHashSet<>(collection);
    Document[] arraySortedCollection = sortedCollection.toArray(new Document[collection.size()]);
    Arrays.sort(
        arraySortedCollection,
        (o1, o2) -> Double.compare(o2.getScore(), o1.getScore())
    );
    sortedCollection = new LinkedHashSet<>(Arrays.asList(arraySortedCollection));
    return filterByTerm(sortedCollection, term).collect(Collectors.toList());
  }

  /**
   * @param document The document to define the score
   * @param term Term that occurs in document
   */
  private void scoreTFIDF(Document document, String term) {
    int amountOfWords = document.getContent().size();
    int amountOfDocuments = collection.size();
    int amountOfFoundSearchTerm = (int) document
        .getContent()
        .stream()
        .filter(word -> word.equals(term))
        .count();
    int amountOfDocumentsTermOccur =
        (int) collection
            .stream()
            .filter(doc -> doc.getContent().stream().anyMatch(word -> word.equals(term)))
            .count();
    double tf = (double) amountOfFoundSearchTerm / amountOfWords;
    double idf = log10((double) amountOfDocuments / amountOfDocumentsTermOccur);
    document.setScore(tf * idf);
  }

  /**
   * Filter documents by term. Method returns only documents that contain term
   *
   * @param term Filter argument
   * @return Stream of filtered documents
   */
  private Stream<Document> filterByTerm(Set<Document> collection, String term) {
    return collection
        .stream()
        .filter(doc ->
            doc
                .getContent()
                .stream()
                .anyMatch(t -> t.equals(term))
        );
  }
}
