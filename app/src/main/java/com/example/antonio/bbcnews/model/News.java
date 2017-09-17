package com.example.antonio.bbcnews.model;

import java.util.List;

/**
 * Created by antonio on 14/09/2017.
 */

public class News {

    private String status;
    private String source;
    private String sortBy;
    private List<Articles> articles;

    public News(String status, String source, String sortBy, List<Articles> articles) {
        this.status = status;
        this.source = source;
        this.sortBy = sortBy;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
