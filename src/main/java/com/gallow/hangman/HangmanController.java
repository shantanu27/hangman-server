package com.gallow.hangman;

import com.gallow.hangman.model.State;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hangman")
public class HangmanController {

    @RequestMapping(path = "/play", method = RequestMethod.GET)
    public String play(@PathVariable(value = "token", required = false) String token,
                      @PathVariable(value = "guess", required = false) String guess) {
        return "success";
    }
}
