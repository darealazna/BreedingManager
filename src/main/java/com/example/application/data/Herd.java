package com.example.application.data;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class Herd extends AbstractEntity{

    private String name;
    private String description;
    @OneToMany
    private List<Cattle> cattle;

}
