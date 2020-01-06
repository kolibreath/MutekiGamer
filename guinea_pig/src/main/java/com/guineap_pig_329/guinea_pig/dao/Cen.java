package com.guineap_pig_329.guinea_pig.dao;

public class Cen {
    private String url;
    private String repoName;
    private String endpoint;
    private String accessKeyIdl;
    private String accessKeySecrect;
    private String bucketName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyIdl() {
        return accessKeyIdl;
    }

    public void setAccessKeyIdl(String accessKeyIdl) {
        this.accessKeyIdl = accessKeyIdl;
    }

    public String getAccessKeySecrect() {
        return accessKeySecrect;
    }

    public void setAccessKeySecrect(String accessKeySecrect) {
        this.accessKeySecrect = accessKeySecrect;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public Cen(){}

    public Cen(String url, String repoName, String endpoint, String accessKeyIdl, String accessKeySecrect, String bucketName) {
        this.url = url;
        this.repoName = repoName;
        this.endpoint = endpoint;
        this.accessKeyIdl = accessKeyIdl;
        this.accessKeySecrect = accessKeySecrect;
        this.bucketName = bucketName;
    }
}
