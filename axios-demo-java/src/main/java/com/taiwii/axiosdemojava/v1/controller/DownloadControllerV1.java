package com.taiwii.axiosdemojava.v1.controller;

import com.taiwii.axiosdemojava.bizerror.BizError;
import com.taiwii.axiosdemojava.exception.BizException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/v1/download")
public class DownloadControllerV1 {

    @GetMapping("/getDownload")
    public ResponseEntity<byte[]> getDownload() throws IOException, URISyntaxException {
        URL url = this.getClass().getResource("/static/中文.jpg");
        File file = new File(url.toURI().getPath());

        FileInputStream fis = new FileInputStream(file);
        return ResponseEntity.ok()
                .headers(this.getHeaders(file.getName()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(fis.readAllBytes());
    }

    @GetMapping("/getDownloadBizError")
    public ResponseEntity<byte[]> getDownloadBizError() throws IOException, URISyntaxException {
        throw new BizException(BizError.INVALID_PARAM);
    }

    @GetMapping("/getImage")
    public ResponseEntity<InputStreamResource> getImage(@RequestParam("type") String type) throws IOException, URISyntaxException {
        MediaType contentType;
        String path;

        switch (type) {
            case "jpg":
            case "jpeg":
                contentType = MediaType.IMAGE_JPEG;
                path = "/static/中文.jpg";
                break;
            case "png":
                contentType = MediaType.IMAGE_PNG;
                path = "/static/中文.png";
                break;
            default:
                type = "png";
                contentType = MediaType.APPLICATION_OCTET_STREAM;
                path = "/static/中文.png";
        }

        File file = new File(this.getClass().getResource(path).toURI().getPath());
        return ResponseEntity.ok()
                .headers(this.getHeaders(file.getName()))
                .contentType(contentType)
                .contentLength(file.length())
                .body(new InputStreamResource(new FileInputStream(file)));
    }

    //region postDownload
    @PostMapping("/postDownload")
    public ResponseEntity<byte[]> postDownload(@RequestBody PostDownloadRequest request) throws IOException, URISyntaxException {
        URL url = this.getClass().getResource("/static/" + request.getFileName());
        File file = new File(url.toURI().getPath());

        FileInputStream fis = new FileInputStream(file);
        return ResponseEntity.ok()
                .headers(this.getHeaders(file.getName()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(fis.readAllBytes());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDownloadRequest {
        private String fileName;
    }
    //endregion

    private HttpHeaders getHeaders(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition
                .attachment()            // 附件形式
                .filename(fileName, StandardCharsets.UTF_8)  // 文件名称 & 编码
                .build()
                .toString());
        return headers;
    }

    public static void main(String[] args) {
        String fileName = "中文.jpg";
        ContentDisposition contentDisposition = ContentDisposition
                .attachment()            // 附件形式
                .filename(fileName)  // 文件名称 & 编码
                .build();
        System.out.println(contentDisposition.toString());
    }
}
