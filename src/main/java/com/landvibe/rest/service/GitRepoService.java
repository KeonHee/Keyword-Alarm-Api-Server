package com.landvibe.rest.service;

import com.landvibe.rest.domain.GItRepo;

import java.util.List;

/**
 * Created by user on 2017-05-01.
 */
public interface GitRepoService {

    List<GItRepo> searchGitRepository(String query);
}
