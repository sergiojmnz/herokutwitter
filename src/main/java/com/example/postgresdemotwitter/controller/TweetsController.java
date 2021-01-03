package com.example.postgresdemotwitter.controller;

import com.example.postgresdemotwitter.exception.ResourceNotFoundException;
import com.example.postgresdemotwitter.model.Tweet;
import com.example.postgresdemotwitter.repository.TweetRepository;
import com.example.postgresdemotwitter.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.context.annotation.ComponentScan; 


@RestController
public class TweetsController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios/{usuarioId}/tweets")
    public List<Tweet> getTweetsByUsuariosId(@PathVariable Long UsuarioId) {
        return tweetRepository.findByUsuarioId(UsuarioId);
    }

    @PostMapping("/usuarios/{usuarioId}/tweets")
    public Tweet addTweet(@PathVariable Long usuarioId,
                            @RequestBody Tweet tweet) {
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    tweet.setUsuario(usuario);
                    return tweetRepository.save(tweet);
                }).orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + usuarioId));
    }

    @PutMapping("/usuarios/{usuarioId}/tweets/{tweetId}")
    public Tweet updateTweet(@PathVariable Long usuarioId,
                               @PathVariable Long tweetId,
                            @RequestBody Tweet tweetRequest) {
        if(!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario not found with id " + usuarioId);
        }

        return tweetRepository.findById(tweetId)
                .map(tweet -> {
                    tweet.setText(tweetRequest.getText());
                    return tweetRepository.save(tweet);
                }).orElseThrow(() -> new ResourceNotFoundException("Tweet not found with id " + tweetId));
    }

    @DeleteMapping("/usuarios/{usuarioId}/tweets/{tweetId}")
    public ResponseEntity<?> deleteTweet(@PathVariable Long usuarioId,
                                          @PathVariable Long tweetId) {
        if(!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario not found with id " + usuarioId);
        }

        return tweetRepository.findById(tweetId)
                .map(tweet -> {
                    tweetRepository.delete(tweet);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("tweet not found with id " + tweetId));

    }
}