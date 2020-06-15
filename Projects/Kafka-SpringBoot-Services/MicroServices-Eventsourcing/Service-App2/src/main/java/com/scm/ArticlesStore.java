package com.scm;

import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArticlesStore {

    private Map<String,Article> articles = new HashMap<>(10000000);

    public void save(Article article) {
    	System.out.println("\n***ArticlesStore Save\n***");
        articles.put(article.getId(),article);
    }

    public Collection<Article> getAll() {
    	System.out.println("\n***ArticlesStore GetAll\n***");
        return articles.values();
    }

    public Article get(String id) {
    	System.out.println("\n***ArticlesStore Get\n***");
        return articles.get(id);
    }

    public long getSize() {
    	System.out.println("\n***ArticlesStore GetSize\n***");
        return articles.size();
    }

    public void delete(String key) {
    	System.out.println("\n***ArticlesStore Delete\n***");
        articles.remove(key);
    }

}
