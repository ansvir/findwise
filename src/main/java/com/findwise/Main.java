package com.findwise;

import com.findwise.model.Document;
import com.findwise.service.DefaultDocumentSearchEngine;
import com.findwise.util.DocumentParser;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    System.out.println("Findwise Search Engine\n");
    Scanner input = new Scanner(System.in);
    String word;
    SearchEngine engine = new DefaultDocumentSearchEngine();
    engine.indexDocument("document1.txt", DocumentParser.getDocumentContentByName("document/document1.txt"));
    engine.indexDocument("document2.txt", DocumentParser.getDocumentContentByName("document/document2.txt"));
    engine.indexDocument("document3.txt", DocumentParser.getDocumentContentByName("document/document3.txt"));
    do {
      System.out.println("Please type the word to search:\n");
      word = input.nextLine().trim();



    } while (!word.matches("^\\w+$"));
  }
}
