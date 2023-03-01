package com.tasks.octotasks2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ApiConsumerController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/posts")
    public Post[] fetchpostList() {
        ResponseEntity<Post[]> response =
                restTemplate.getForEntity(
                        "https://jsonplaceholder.typicode.com/posts",
                        Post[].class);
        Post[] employees = response.getBody();
        return employees;
    }

    @PostMapping("/posts")
    public Post saveEmployee(@RequestBody Post post) {
        Post response=restTemplate.postForObject(
                "https://jsonplaceholder.typicode.com/posts",
                post,
                Post.class);
        return response;
    }

    @GetMapping("/posts/{id}")
    public Post getEmployeeById(@PathVariable("id") int id) {
        ResponseEntity<Post> response =restTemplate.getForEntity(
                        "https://jsonplaceholder.typicode.com/posts/"+id,
                        Post.class);
        Post post=response.getBody();
        return post;
    }
}
