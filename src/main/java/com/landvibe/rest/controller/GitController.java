package com.landvibe.rest.controller;

import com.landvibe.rest.domain.GItRepo;
import com.landvibe.rest.service.GitRepoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017-05-01.
 */

@RestController
public class GitController {

    @Resource(name = "GitRepoService")
    GitRepoService gitRepoService;

    @RequestMapping(value = "/git/search/{query}", method = RequestMethod.GET)
    public List<GItRepo> searchGitRepository(@PathVariable String query){
        List<GItRepo> gItRepoList = gitRepoService.searchGitRepository(query);
        return gItRepoList;
    }

}
