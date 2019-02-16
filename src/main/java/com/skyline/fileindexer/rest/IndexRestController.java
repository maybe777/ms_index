package com.skyline.fileindexer.rest;

import com.skyline.fileindexer.dto.DocInfo;
import com.skyline.fileindexer.feign.StorageFeignClient;
import com.skyline.fileindexer.model.DocIndex;
import com.skyline.fileindexer.repo.IndexRepository;
import com.skyline.fileindexer.service.IndexCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.*;


@RestController
@RequestMapping("api")
public class IndexRestController {

    private IndexRepository repository;
    private IndexCrudService service;
    private StorageFeignClient feignClient;

    @Autowired
    public IndexRestController(IndexRepository repository, IndexCrudService service, StorageFeignClient feignClient) {
        this.repository = repository;
        this.service = service;
        this.feignClient = feignClient;
    }


    @PostMapping("/getindex")
    List<DocIndex> getIndexes(@RequestParam("words") List<String> words) {
        return service.getIndexDocByWords(words);
    }

    @PostMapping("/updateindex")
    ResponseEntity<String> getRefrences(@RequestBody DocInfo docInfo) {

        String content = docInfo.getContent();
        String[] words = content.split(" ");

        List<DocIndex> indexDocs = service.getIndexDocByWords(Arrays.asList(words));

        List<String> wordsUpdated = new ArrayList<>();
        for (DocIndex index : indexDocs) {
            List<Long> references = index.getReferences();
            wordsUpdated.add(index.getWord());
            if (!references.contains(docInfo.getId())) {
                references.add(docInfo.getId());
                references.sort(Comparator.naturalOrder());

                index.setReferences(references);

                repository.save(index);
            }
        }

        for (String word : words) {
            if (!wordsUpdated.contains(word)) {
                repository.save(new DocIndex(word, Collections.singletonList(docInfo.getId())));
            }
        }
        return ResponseEntity.ok("Ok!");

    }

}
