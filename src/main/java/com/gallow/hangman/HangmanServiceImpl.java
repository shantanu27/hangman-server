package com.gallow.hangman;

import com.gallow.hangman.dao.PhraseDao;
import com.gallow.hangman.dao.TokenStateDao;
import com.gallow.hangman.model.Phrase;
import com.gallow.hangman.model.ResponseState;
import com.gallow.hangman.model.TokenState;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HangmanServiceImpl implements HangmanService{

    @Autowired
    private PhraseDao phraseDao;

    @Autowired
    private TokenStateDao tokenStateDao;

    private final int maxGuesses = 3;

    private final String ALIVE = "ALIVE";
    private final String DEAD = "DEAD";
    private final String FREE = "FREE";

    @Override
    public ResponseState startNewGame() {
        String token = generateToken();
        Phrase phrase = getRandomPhrase();
        String stateString = phrase.getPhrase().replaceAll("[a-zA-Z]", "_");
        TokenState tokenState = new TokenState(token, phrase, maxGuesses, stateString);

        tokenStateDao.save(tokenState);

        return new ResponseState(ALIVE, token, maxGuesses, stateString);
    }

    private Phrase getRandomPhrase() {
        /*
            Select random row from the phrases table.
         */
        Pageable topOne = PageRequest.of(0, 1);
        return phraseDao.getRandomRow(topOne).get(0);
    }

    @Override
    public ResponseState updateGameState(String token, String guess) {
        Optional<TokenState> tokenState = tokenStateDao.findById(token);

        ResponseState responseState = new ResponseState();

        if (tokenState.isPresent()) {
            TokenState ts = tokenState.get();
            // Set initial response to current state
            responseState = new ResponseState(calculateStatus(ts.getState(), ts.getRemainingGuesses()),
                                              token,
                                              ts.getRemainingGuesses(),
                                              ts.getState());

            if (ts.getRemainingGuesses() >= 0 &&
                !calculateStatus(ts.getState(), ts.getRemainingGuesses()).equals(FREE)) {
                String phrase = ts.getPhrase().getPhrase();

                /*
                    If phrase contains guessed char, then update state
                    Else update remaining guesses and status accordingly.
                 */
                StringBuilder state = new StringBuilder(ts.getState());
                String status;
                int remainingGuesses;

                int index = phrase.indexOf(guess);
                boolean foundChar = false;
                while (index >= 0) {
                    foundChar = true;
                    state.setCharAt(index, guess.charAt(0));
                    index = phrase.indexOf(guess, index + 1);
                }

                remainingGuesses = foundChar ? ts.getRemainingGuesses() : ts.getRemainingGuesses() - 1;
                status = calculateStatus(state.toString(), remainingGuesses);

                ts.setState(state.toString());
                ts.setRemainingGuesses(remainingGuesses);
                tokenStateDao.save(ts);

                responseState.setStatus(status);
                responseState.setRemainingGuesses(remainingGuesses);
                responseState.setState(state.toString());
            }
        } else {
            responseState.setStatus("Invalid Request");
        }
        return responseState;
    }

    private String calculateStatus(String state, int remainingGuesses) {
        if (!state.contains("_")) {
            return FREE;
        } else {
            if (remainingGuesses >= 0) {
                return ALIVE;
            } else {
                return DEAD;
            }
        }
    }

    private String generateToken() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
