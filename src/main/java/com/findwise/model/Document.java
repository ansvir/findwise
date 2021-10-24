package com.findwise.model;

import com.findwise.IndexEntry;
import java.util.List;

public class Document implements IndexEntry {
  private String id;
  private List<String> content;
  private double score;

  public Document(String id, List<String> content) {
    this.id = id;
    this.content = content;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<String> getContent() {
    return content;
  }

  public void setContent(List<String> content) {
    this.content = content;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }
}
