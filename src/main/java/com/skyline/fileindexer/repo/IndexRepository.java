package com.skyline.fileindexer.repo;

import com.skyline.fileindexer.model.DocIndex;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IndexRepository extends MongoRepository<DocIndex, String> {

}
