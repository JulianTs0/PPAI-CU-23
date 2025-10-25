package com.mycompany.ppai_cu_23.models;

import com.mycompany.ppai_cu_23.utils.DataBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    
    private String ambito;
    private String nombreEstado;
    
    // METODOS DOMINIO
    
    // COMPARACION ESTADOS
    public boolean esPendienteDeRevision(){
        return (this.nombreEstado.equals(DataBase.nombresEstado.Pendiente_De_Revision.name()));
    }
    
    public boolean esBloqueadoARevisar(){
        return (this.nombreEstado.equals(DataBase.nombresEstado.Bloqueado_En_Revision.name()));
    }

    public boolean esRechazado(){
        return (this.nombreEstado.equals(DataBase.nombresEstado.Rechazado.name()));
    }

    public boolean esAutoDetectado(){
        return (this.nombreEstado.equals(DataBase.nombresEstado.Auto_Detectado.name()));
    }

    //COMPARACION AMBITOS
    public boolean esAmbitoEventoSismico(){
        return (this.ambito.equals(DataBase.nombresAmbito.Evento_Sismico.name()));
    }

    

}
