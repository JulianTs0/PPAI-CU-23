package com.mycompany.ppai_cu_23.refactor;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "estado_auto_detectado")
public class AutoDetectado extends Estado {

    public AutoDetectado(String ambito, String nombre) {
        super(ambito, nombre);
    }
}
