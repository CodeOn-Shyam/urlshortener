package com.codeon.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.codeon.urlshortener.service.ShortUrlService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.codeon.urlshortener.entity.Url;

@Controller
public class WebController {
    private final ShortUrlService shortUrlService;

    WebController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home() {
        return "index";
    }
    @RequestMapping("/{shortCode}")
    public ModelAndView reditrect(@PathVariable String shortCode){
        Url url = shortUrlService.getOriginalUrl(shortCode);
        return new ModelAndView("redirect:" + url.getUrl());
    }
}
