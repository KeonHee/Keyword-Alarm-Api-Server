package com.landvibe.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.landvibe.domain.GItRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.*;

/**
 * Created by user on 2017-05-01.
 */
@Service("GitRepoService")
public class GItRepoServiceImpl implements GitRepoService {
    @Override
    public List<GItRepo> searchGitRepository(String query) {

        /* HttpComponentsClientHttpRequestFactory 사용시 HttpClient NotFoundException 발생*/
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setReadTimeout(1000 * 60 * 2);  // 2분
//        factory.setConnectTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(); //@TODO Timeout 설정하기

        //https://api.github.com/search/repositories?q=tetris+language:assembly&sort=stars&order=desc
        URI gitHubSearchUri = UriComponentsBuilder.newInstance().scheme("https").host("api.github.com")
                .path("/search/repositories")
                .queryParam("q",query)
                .queryParam("sort","stars")
                .queryParam("order","desc")
                .build()
                .toUri();

        String response = restTemplate.getForObject(gitHubSearchUri,String.class); //@TODO response 로그찍기

        List<GItRepo> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode items = jsonNode.get("items");
            Iterator<JsonNode> itemsIter = items.elements();
            while (itemsIter.hasNext()){
                JsonNode item = itemsIter.next();
                GItRepo gitRepo = new GItRepo();
                gitRepo.setId(item.get("id").asLong());
                gitRepo.setName(item.get("name").asText());
                gitRepo.setDescription(item.get("description").asText());
                gitRepo.setUrl(item.get("url").asText());
                gitRepo.setCreated_at(item.get("created_at").asText());
                list.add(gitRepo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
