package com.example.application.data;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
public class Cattle extends AbstractEntity {

    private String number;
    private boolean gender; //true = female
    private LocalDate dateOfBirth;

}
