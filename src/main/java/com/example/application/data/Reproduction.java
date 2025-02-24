package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter


public class Reproduction extends AbstractEntity {

    private LocalDateTime eventDateTime;
    private String category;
    @ManyToOne
    private Cattle cattle;
    private String description;
    @ManyToOne
    private Cattle father;
    private String fatherNumber;




}
