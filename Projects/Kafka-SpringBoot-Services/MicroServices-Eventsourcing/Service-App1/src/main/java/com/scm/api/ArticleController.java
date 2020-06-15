package com.scm.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scm.ArticlesStore;

import com.scm.Article;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

import static java.lang.Math.random;
import static java.lang.Math.round;

@RestController
@RequestMapping("/article-app1")
public class ArticleController {

    @Autowired
    private ArticlesStore store;

    Gson gson = new Gson();

    @Autowired
    public Producer<String,String> producer;

    @GetMapping
    public Collection<Article> getArticles() {
        return store.getAll();
    }

    JsonParser parser = new JsonParser();

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable("id") String id) {

        Article a = store.get(id);

        if (a == null) {
            throw new IllegalArgumentException();
        }

        return a;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Article article,
                                     UriComponentsBuilder uriBuilder) {

    	System.out.println("Creating new Article in Service-App1");
        String id = UUID.randomUUID().toString();
        article.setId(id);

        producer.send(new ProducerRecord<>("articles", id, createWrapper( article)));

        URI location = uriBuilder
                .path("/articles/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }



    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
    	System.out.println("Deletign the  Article in Service-App1 with id="+id);
        producer.send(new ProducerRecord<>("articles", id, deleteWrapper(id)));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") String id, @RequestBody Article article) {
    	System.out.println("Updating the  Article in Service-App1 with id="+id);
        producer.send(new ProducerRecord<>("articles", id, putWrapper(article)));
    }

    @GetMapping("count")
    public long getCount() {
    	
        return store.getSize();
    }

    private String createWrapper( Article article) {
        JsonObject cmd = new JsonObject();
        cmd.addProperty("action", "create");
        cmd.add("object", gson.toJsonTree(article));
        return cmd.toString();
    }

    private String deleteWrapper( String id) {
        JsonObject cmd = new JsonObject();
        cmd.addProperty("action", "delete");
        return cmd.toString();
    }

    private String putWrapper( Article article) {
        JsonObject cmd = new JsonObject();
        cmd.addProperty("action", "update");
        cmd.add("object", gson.toJsonTree(article));
        return cmd.toString();
    }
}
