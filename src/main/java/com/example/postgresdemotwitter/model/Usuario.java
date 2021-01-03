package com.example.postgresdemotwitter.model;
import org.springframework.context.annotation.ComponentScan; 


import javax.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario extends AuditModel {
    @Id
    @GeneratedValue(generator = "usuario_generator")
    @SequenceGenerator(
            name = "usuario_generator",
            sequenceName = "usuario_sequence",
            initialValue = 1000
    )
    private Long id;

    

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    // Getters and Setters (Omitted for brevity)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
