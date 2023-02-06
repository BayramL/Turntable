package com.techelevator.controller;

import com.techelevator.services.SpotifyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    private SpotifyService service = new SpotifyService();

    @GetMapping("/spotify-test")
    public void test() {
        String token = service.getToken();
    }

    @GetMapping("/spotify-test2/{search}")
    public void test2(@PathVariable String search) {
        service.searchSongId(search);
    }
}
