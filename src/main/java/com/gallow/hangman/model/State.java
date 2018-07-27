package com.gallow.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State {

    private String status;
    private String token;
    private int remainingGuesses;
    private String state;
}
