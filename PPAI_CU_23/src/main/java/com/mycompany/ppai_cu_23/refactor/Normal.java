package com.mycompany.ppai_cu_23.refactor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estado_normal")
public class Normal extends Estado{

    public Normal(String ambito, String nombre) {
        super(ambito, nombre);
    }

}
