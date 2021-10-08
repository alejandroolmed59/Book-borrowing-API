package com.olmedo.bookborrowing.controller;


import com.olmedo.bookborrowing.entity.Genre;
import com.olmedo.bookborrowing.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping("/")
    public List<Genre> getGenre() {
        List<Genre> genres = genreService.findAll();
        return genres;
    }

    @PostMapping("/")
    public Genre create(@RequestBody Genre genre) throws Exception {
        Genre createdGenre = genreService.create(genre);

        if (createdGenre==null) {
            throw new Exception("Genre was not created");
        } else {
            return createdGenre;
        }
    }
}
