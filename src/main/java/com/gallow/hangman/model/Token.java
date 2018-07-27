package com.gallow.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "tokens")
public class Token {

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @ManyToOne
    @JoinColumn(name = "phrase_id", referencedColumnName = "phrase_id")
    private Phrase phrase;

    private String state;
}
