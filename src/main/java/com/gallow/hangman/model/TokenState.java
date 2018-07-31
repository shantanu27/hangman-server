package com.gallow.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "token_state")
public class TokenState implements Serializable{

    @Id
    private String token;

    @ManyToOne
    @JoinColumn(name = "phrase_id", referencedColumnName = "phrase_id")
    private Phrase phrase;

    @Column(name = "remaining_guesses")
    private int remainingGuesses;

    private String state;
}
