package com.landvibe.rest.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017-05-02.
 */
@RestController
public class CrawlTestController {

    @RequestMapping(value = "/crawltest", method = RequestMethod.GET)
    public String authInhaPlaza(){
        RestTemplate restTemplate = new RestTemplate(); //@TODO Timeout 설정하기


        /**
         *  인증정보 요청
         */
        //http://www.inha.ac.kr/plaza/memberLoginBefore.do
        URI url = UriComponentsBuilder.newInstance().scheme("http").host("www.inha.ac.kr")
                .path("/plaza/memberLoginBefore.do")
                .build()
                .toUri();

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("userId","12121442");
        parameters.add("userPw","password"); //@TODO password 입력!

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        HttpHeaders responseHeaders = response.getHeaders();
        String cookies[] = responseHeaders.get("Set-Cookie").get(0).split("=|;");
        for (String cookie : cookies){
            System.out.println("cookie : " + cookie);
        }
        String sessionId=cookies[1];

        /**
         *  게시글 요청
         *  > header의 location으로 상세 url 응답
         */
        //http://www.inha.ac.kr/user/boardList.do?command=view&boardId=235757&boardSeq=5408968&id=plaza_010100000000
        URI dataUrl = UriComponentsBuilder.newInstance().scheme("http").host("www.inha.ac.kr")
                .path("/user/boardList.do")
                .queryParam("command","view")
                .queryParam("boardId","235757")
                .queryParam("boardSeq","5408968")
                .queryParam("id","plaza_010100000000")
                .build()
                .toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", "JSESSIONID=" + sessionId+";");

        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        HttpEntity<String> responsePage = restTemplate.exchange(dataUrl,HttpMethod.GET,requestEntity,String.class);

        HttpHeaders responseHeader = responsePage.getHeaders();
        URI location = responseHeader.getLocation();
        System.out.println(location);

        return location.toString();
    }
}
