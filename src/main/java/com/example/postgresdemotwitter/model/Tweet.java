package com.example.postgresdemotwitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.ComponentScan; 


@Entity
@Table(name = "tweets")
public class Tweet extends AuditModel {
    @Id
    @GeneratedValue(generator = "tweet_generator")
    @SequenceGenerator(
            name = "tweet_generator",
            sequenceName = "tweet_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Usuario usuario;

    // Getters and Setters (Omitted for brevity)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}