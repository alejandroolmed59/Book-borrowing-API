package com.olmedo.bookborrowing.controller;


import com.olmedo.bookborrowing.entity.Genre;
import com.olmedo.bookborrowing.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Genre newValues, @PathVariable Integer id) throws Exception {
        Genre found = genreService.findById(id);

        if (found == null) {
            throw new Exception("Role were not found with id=" + id);
        } else {
            found = genreService.update(found, newValues);
            return ResponseEntity.ok(found);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) throws Exception {
        Genre found = genreService.findById(id);

        if (found == null) {
            throw new Exception("Role were not found with id=" + id);
        } else {
            genreService.delete(found);
            return ResponseEntity.ok().body("Eliminado");
        }
    }
}
