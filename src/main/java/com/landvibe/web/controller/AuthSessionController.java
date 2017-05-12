package com.landvibe.web.controller;

import com.landvibe.domain.AuthSession;
import com.landvibe.web.service.AuthSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by user on 2017-05-13.
 */
@Controller
@EnableAutoConfiguration
public class AuthSessionController {

    @Resource(name = "AuthSessionService")
    private AuthSessionService authSessionService;

    @RequestMapping(value = "/auth-sessions", method = RequestMethod.GET)
    public String retrieveSession(Model model){
        AuthSession session = authSessionService.findLastOne();
        if(session==null){
            session = new AuthSession("nothing");
        }
        model.addAttribute("sessionInfo", session);
        model.addAttribute("authSession", new AuthSession());
        return "session-info";
    }

    @RequestMapping(value = "/auth-sessions", method = RequestMethod.POST)
    public String addSession(@ModelAttribute AuthSession authSession,
                             BindingResult bindingResult, Model model){
        AuthSession createdAuthSession = authSessionService.addSession(authSession);
        // @TODO 로그찍기
        return "redirect:/auth-sessions";
    }

    @RequestMapping(value = "/auth-sessions",method = RequestMethod.DELETE)
    public String deleteAllSession(){
        authSessionService.deleteAll();
        return "redirect:/auth-sessions";
    }

}
