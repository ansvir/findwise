package com.findwise.model;

import com.findwise.IndexEntry;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document implements IndexEntry {
  private String id;
  private List<Word> content;
  private double score;

  public Document(String id, List<Word> content) {
    this.id = id;
    this.content = content;
  }
}
