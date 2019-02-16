package com.skyline.fileindexer.feign;

import com.skyline.fileindexer.dto.DocInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(name="storage")
public interface StorageFeignClient {
    @PostMapping(value="/api/file/get")
    List<DocInfo> getFiles(@RequestParam("id") List<Long> val);
}
