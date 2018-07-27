package com.gallow.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "phrases")
public class Phrase {

    @Id
    @Column(name = "phrase_id")
    private Integer phraseId;

    @Column(name = "phrase")
    private String phrase;
}
