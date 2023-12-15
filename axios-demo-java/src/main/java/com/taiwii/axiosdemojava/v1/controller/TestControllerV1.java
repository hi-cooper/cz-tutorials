package com.taiwii.axiosdemojava.v1.controller;

import com.taiwii.axiosdemojava.bizerror.BizError;
import com.taiwii.axiosdemojava.response.HttpApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/test")
public class TestControllerV1 {

    //region get
    @GetMapping("/get200")
    public HttpApiResponse<String> get200() {
        return HttpApiResponse.newSuccessInstance("hello");
    }
    @GetMapping("/get200BizError")
    public HttpApiResponse<String> get200BizError() {
        return HttpApiResponse.newErrorInstance(BizError.INVALID_PARAM);
    }
    @GetMapping("/get2xx")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public HttpApiResponse<String> get2xx() {
        return null;
    }
    @GetMapping("/get5xx")
    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    public HttpApiResponse<String> get5xx() {
        return null;
    }
    //endregion

    //region create
    @PostMapping("/create")
    public HttpApiResponse<CreateResponse> create(@RequestBody CreateRequest request) {
        return HttpApiResponse.newSuccessInstance(new CreateResponse(String.format("testId=%s, name=%s", request.getTestId(), request.getName())));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRequest {
        private long testId;
        private String name;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResponse {
        private String message;
    }
    //endregion

    //region upload
    @PostMapping(path = "/upload")
    public HttpApiResponse<List<String>> upload(@ModelAttribute UploadRequest request) {
        List<String> fileNames = new ArrayList<>();
        MultipartFile[] files = request.getFiles();
        if (null != files) {
            for (MultipartFile file : files) {
                fileNames.add(String.format("name=%s, size=%s", file.getOriginalFilename(), file.getSize()));
            }
        }
        return HttpApiResponse.newSuccessInstance(fileNames);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadRequest {
        private long testId;
        private MultipartFile[] files;
    }
    //endregion
}
