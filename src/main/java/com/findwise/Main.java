package com.findwise;

import com.findwise.service.DefaultDocumentSearchEngine;
import com.findwise.util.DocumentParser;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    System.out.println("Findwise Search Engine");
    Scanner scanner = new Scanner(System.in);
    String input;
    DefaultDocumentSearchEngine engine = new DefaultDocumentSearchEngine();
    engine.indexDocument("document1.txt", DocumentParser.getDocumentContentByName("document/document1.txt"));
    engine.indexDocument("document2.txt", DocumentParser.getDocumentContentByName("document/document2.txt"));
    engine.indexDocument("document3.txt", DocumentParser.getDocumentContentByName("document/document3.txt"));
    engine.indexDocument("document4.txt", DocumentParser.getDocumentContentByName("document/document4.txt"));
    do {
      System.out.println("Please type the word to search:");
      input = scanner.nextLine().trim();
      for (IndexEntry entry : engine.search(input)) {
        System.out.println("[" + entry.getId() + "] - {" + entry.getScore() + "}");
      }
      System.out.println("Sort? (y\\n):");
      String choice = scanner.nextLine().trim();
      if (choice.equals("y")) {
        for (IndexEntry entry : engine.sortByTFIDF(input)) {
          System.out.println("[" + entry.getId() + "] - {" + entry.getScore() + "}");
        }
      }
      System.out.println("Continue? (y\\n):");
    } while (!scanner.nextLine().equals("n"));
  }
}
