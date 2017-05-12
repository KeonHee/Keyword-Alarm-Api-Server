package com.landvibe.web.controller;

import com.landvibe.domain.Post;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2017-04-29.
 */

@Controller
@EnableAutoConfiguration
public class PostController {

    @RequestMapping(value = "/posts/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "new";
    }

    @RequestMapping(value = "/posts",method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post, Model model){
        model.addAttribute("post",post);
        return "show";
    }

    @RequestMapping(value="/posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> list(){
        List posts = Arrays.asList(new Post(),  new Post());
        return posts;
    }

}
