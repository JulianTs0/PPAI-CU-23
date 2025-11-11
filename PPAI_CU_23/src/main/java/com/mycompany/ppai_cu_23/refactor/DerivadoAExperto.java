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
@Table(name = "estado_derivado_a_experto")
public class DerivadoAExperto extends Estado {

    public DerivadoAExperto(String ambito, String nombre) {
        super(ambito, nombre);
    }

}
