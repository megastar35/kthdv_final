package com.fsoft.fa.interviewprocessmanagement.controller;

import com.fsoft.fa.interviewprocessmanagement.model.UploadFile;
import com.fsoft.fa.interviewprocessmanagement.service.FileStorageService;
import com.fsoft.fa.interviewprocessmanagement.service.UploadFileService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.fsoft.fa.interviewprocessmanagement.service.DataTableService.GSON;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private FileStorageService fileStorageService;
    private ResourceLoader resourceLoader;
    private UploadFileService uploadFileService;

    @Autowired
    public void setUploadFileService(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    public void setFileStorageService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public JsonObject populateUploadStatus(UploadFile uploadedFile) {
        JsonObject result = new JsonObject();
        JsonObject file = new JsonObject();
        JsonObject files = new JsonObject();
        file.add(String.valueOf(uploadedFile.getId()), GSON.toJsonTree(uploadedFile));
        files.add("files", file);
        JsonObject upload = new JsonObject();
        upload.addProperty("id", uploadedFile.getId());
        result.add("data", new JsonArray());
        result.add("files", files);
        result.add("upload", upload);
        return result;
    }

    @PostMapping(value = "/uploadFile", produces = "application/json")
    public String uploadFile(@RequestParam("upload") MultipartFile image) throws IOException {
        String fileName = fileStorageService.storeFile(image);
        String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String webPath = "/uploads/" + fileName;
        System.out.println("WebPath: " + webPath);
        UploadFile uploadedFile = new UploadFile();
        uploadedFile.setFileName(fileName);
        uploadedFile.setFileType(image.getContentType());
        uploadedFile.setFileSize(image.getSize());
        uploadedFile.setWebPath(webPath);
        uploadedFile.setDownloadUri(downloadUri);
        uploadFileService.save(uploadedFile);
        return populateUploadStatus(uploadedFile).toString();
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
