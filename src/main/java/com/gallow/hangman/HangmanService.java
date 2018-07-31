package com.gallow.hangman;

import com.gallow.hangman.model.ResponseState;

public interface HangmanService {

    ResponseState startNewGame();
    ResponseState updateGameState(String token, String guess);
}
