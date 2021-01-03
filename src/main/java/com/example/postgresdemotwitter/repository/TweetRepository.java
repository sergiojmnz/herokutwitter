package com.example.postgresdemotwitter.repository;

import com.example.postgresdemotwitter.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.context.annotation.ComponentScan; 


@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUsuarioId(Long usuarioId);
}