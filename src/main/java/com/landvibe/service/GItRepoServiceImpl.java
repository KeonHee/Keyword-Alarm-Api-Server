package com.landvibe.service;

import com.landvibe.dto.GItRepo;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 2017-05-01.
 */
@Service("GitRepoService")
public class GItRepoServiceImpl implements GitRepoService {
    @Override
    public List<GItRepo> searchGitRepository(String query) {
        //2013-01-05T17:58:47Z
        //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddTkk-mm-ss");

        List<GItRepo> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            GItRepo gItRepo = new GItRepo();
            gItRepo.setId(i);
            gItRepo.setName(query+" "+String.valueOf(i));
            gItRepo.setDescription("test description " + String.valueOf(i));
            gItRepo.setUrl("https://api.github.com/repos/"+query);
            gItRepo.setCreated_at(Calendar.getInstance().getTime());
            list.add(gItRepo);
        }

        return list;
    }
}
