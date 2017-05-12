package com.landvibe.rest.controller;

import com.landvibe.util.RSAUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CrawlingController {

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

        RSAUtils rsaUtils = RSAUtils.getInstance();
        String m = "84d776ac2f36b4f976e5149767f64a420f7db80d6c158cc8be1e06ee7f88f8f9dc25096d92dc4ea2352e4d35dd17071cd26a7231e2789d8829788c989fbe478b516b240094c42958b7fdb77dbfe87f3be5e163f40b558a0646c2ab22af0cb84f0bf88c18b1bb79ed83b46594a7ba8ceb8d94efef292517edf9194efd8942f5d9";
        String e = "10001";
        rsaUtils.init(m,e);
        String encId = rsaUtils.encrypt("12121442");
        String encPassword = rsaUtils.encrypt("inputPw");

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("userId",encId);
        parameters.add("userPw",encPassword); //@TODO password 입력!

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        String responseBody = response.getBody();
        System.out.println("responseBody" + responseBody);

        HttpHeaders responseHeaders = response.getHeaders();
        System.out.println("responseHeaders"+responseHeaders);

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

    @RequestMapping(value = "/reqeust/board/{sessionId}", method = RequestMethod.GET)
    public String requestBoard(@PathVariable String sessionId){
        /**
         *  게시글 요청
         *  > header의 location으로 상세 url 응답
         */

        RestTemplate restTemplate = new RestTemplate();
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
        requestHeaders.add("Cookie", "JSESSIONID="+sessionId+";");

        HttpEntity requestEntity = new HttpEntity(null, requestHeaders);

        HttpEntity<String> responsePage = restTemplate.exchange(dataUrl,HttpMethod.GET,requestEntity,String.class);

        HttpHeaders responseHeader = responsePage.getHeaders();
        String response = responsePage.getBody();
        //URI location = responseHeader.getLocation();
        System.out.println(responseHeader);
        System.out.println(response);

        return response;
    }


}
