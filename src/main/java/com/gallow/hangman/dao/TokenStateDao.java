package com.gallow.hangman.dao;

import com.gallow.hangman.model.TokenState;
import org.springframework.data.repository.CrudRepository;

public interface TokenStateDao extends CrudRepository<TokenState, String>{
}
