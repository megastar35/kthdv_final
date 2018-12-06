package com.fsoft.fa.interviewprocessmanagement.model;

import javax.persistence.*;

@Entity
public class UploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String fileName;

    @Column
    private String webPath;

    @Column
    private String downloadUri;

    @Column
    private String fileType;

    @Column
    private long fileSize;

    public UploadFile() {
    }

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getWebPath() {
        return webPath;
    }

    public void setWebPath(String web_path) {
        this.webPath = web_path;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
