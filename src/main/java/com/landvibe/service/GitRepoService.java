package com.landvibe.service;

import com.landvibe.dto.GItRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-05-01.
 */
public interface GitRepoService {

    List<GItRepo> searchGitRepository(String query);
}
