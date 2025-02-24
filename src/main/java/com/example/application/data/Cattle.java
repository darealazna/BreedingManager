package com.example.application.data;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Cattle extends AbstractEntity {

    private String number;
    private boolean gender; //true = female
    @OneToMany
    private List<Reproduction> reproductionList;

    public boolean isGender() { // Naming convention for boolean getters
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    private LocalDate dateOfBirth;

}
