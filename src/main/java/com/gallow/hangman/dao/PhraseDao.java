package com.gallow.hangman.dao;

import com.gallow.hangman.model.Phrase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhraseDao extends CrudRepository<Phrase, Integer>{

    /**
     * The query works like this:
     * Select all rows, and assign each row a random number
     * Select the top row
     * @return row encapsulated in Phrase object
     */
    @Query("SELECT p FROM phrases p ORDER BY RAND()")
    List<Phrase> getRandomRow(Pageable pageable);
}
