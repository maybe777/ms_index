package com.skyline.fileindexer.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "fileIndex")
public class DocIndex {

    @Id
    private ObjectId id;
    private String word;
    private List<Long> references;

    public DocIndex() {
    }

    public DocIndex(String word, List<Long> references) {
        this.word = word;
        this.references = references;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Long> getReferences() {
        return references;
    }

    public void setReferences(List<Long> references) {
        this.references = references;
    }
}
