package com.olmedo.bookborrowing.service;


import com.olmedo.bookborrowing.entity.Genre;
import com.olmedo.bookborrowing.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    public List<Genre> findAll(){return genreRepository.findAll();}

    public Genre create(Genre newGenre){return genreRepository.save(newGenre);}

    public Genre findById(Integer id) {
        return genreRepository.findByGenreId(id);
    }
    public Genre update(Genre found, Genre newValues) {
        found.setGenreType(newValues.getGenreType());
        return genreRepository.save(found);
    }
    public void delete(Genre found) {
        genreRepository.delete(found);
    }
}
