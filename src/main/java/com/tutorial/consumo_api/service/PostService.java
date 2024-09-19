package com.tutorial.consumo_api.service;

import com.tutorial.consumo_api.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    public List<PostDto> getPost(){
        ResponseEntity<List<PostDto>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/users/1/posts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PostDto>>() {}
        );
        return  response.getBody();
    }

    public PostDto get(Integer id){
        return restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/"+id, PostDto.class).getBody();
    }

    public PostDto save(PostDto postDto){
        return restTemplate.postForObject("https://jsonplaceholder.typicode.com/posts", postDto, PostDto.class);
    }

    public PostDto update(Integer id, PostDto postDto){
        HttpEntity<PostDto> httpEntity = new HttpEntity<>(postDto);
        ResponseEntity<PostDto> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts/"+id,
                HttpMethod.PUT, httpEntity, PostDto.class);
        //restTemplate.put("https://jsonplaceholder.typicode.com/posts/"+id, postDto);
        return response.getBody();
    }

    public void delete(Integer id){
        restTemplate.delete("https://jsonplaceholder.typicode.com/posts/"+id);
    }
}
