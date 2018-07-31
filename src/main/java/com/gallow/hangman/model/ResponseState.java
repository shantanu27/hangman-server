package com.gallow.hangman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseState {
    private String status;
    private String token;

    @JsonProperty("remaining_guesses")
    private Integer remainingGuesses;

    private String state;
}
