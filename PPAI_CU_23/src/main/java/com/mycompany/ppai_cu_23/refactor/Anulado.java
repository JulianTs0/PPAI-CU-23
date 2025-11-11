package com.mycompany.ppai_cu_23.refactor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Requerido por JPA
@Entity
@Table(name = "estado_anulado")
public class Anulado extends Estado {

    public Anulado(String ambito, String nombre) {
        super(ambito, nombre);
    }

}
