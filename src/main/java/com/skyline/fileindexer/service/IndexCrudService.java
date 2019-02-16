package com.skyline.fileindexer.service;

import com.skyline.fileindexer.model.DocIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class IndexCrudService {

    private MongoTemplate template;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public IndexCrudService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<DocIndex> getIndexDocByWords(List<String> words){
        return mongoTemplate.find(query(where("word").in(words)), DocIndex.class);
    }

    public IndexCrudService(MongoTemplate template, MongoTemplate mongoTemplate) {
        this.template = template;
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getTemplate() {
        return template;
    }

    public void setTemplate(MongoTemplate template) {
        this.template = template;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}
