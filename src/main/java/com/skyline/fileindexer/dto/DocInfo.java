package com.skyline.fileindexer.dto;

public class DocInfo {
    private Long id;
    private String content;

    public DocInfo(Long id, String fileName) {
        this.id = id;
        this.content = fileName;
    }

    public DocInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
