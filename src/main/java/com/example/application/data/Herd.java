package com.example.application.data;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.common.value.qual.MinLen;

import java.util.List;

@Entity
@Getter
@Setter
public class Herd extends AbstractEntity{

    @NotBlank(message = "Не сте въвели име")
    @Size(min = 3, max = 20)
    private String name;
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Cattle> cattle;

    public List<Cattle> getCattle() {
        return cattle;
    }
}
