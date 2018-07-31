package com.gallow.hangman;

import com.gallow.hangman.model.ResponseState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/hangman")
public class HangmanController {

    @Autowired
    private HangmanService hangmanService;

    @RequestMapping(path = "/play", method = RequestMethod.GET)
    public ResponseState play(@RequestParam(required = false) String token,
                              @RequestParam(required = false) String guess) {
        if (token == null && guess == null) {
            // New game
            return hangmanService.startNewGame();
        } else if (token != null && guess != null) {
            // Already existing game
            return hangmanService.updateGameState(token, guess.toUpperCase());
        } else {
            return new ResponseState("Bad Request", "", 0, "");
        }
    }
}
