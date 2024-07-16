package com.Desafio.ForoHub.Tema;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Entity
@Table(name = "Tema")
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(of = "id")


public class Tema {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private Long id;
    private String username;
    private String email;
    private String titulo;
    private String curso;
    private String publicacion;
    private LocalDate fechaPublicacion;
    private Boolean activo;

    @Column(name="fecha_Creacion")
    private LocalDate fechaDeCreacion;



    public Tema(DatosRegistroTema datosRegistroTema){
        this.titulo = datosRegistroTema.titulo();
        this.fechaPublicacion =datosRegistroTema.fechaPublicacion();
        this.fechaDeCreacion = datosRegistroTema.fechaDeCreacion();
        this.curso= datosRegistroTema.curso();
        this.username=datosRegistroTema.username();
    }

    public void UpdateDatosTema (UpdateTemaDTO update){
        if (update.titulo() != null) this.titulo=update.titulo();
        if (update.publicacion() != null) this.publicacion=update.publicacion();
        if (update.curso() != null) this.curso=update.curso();
        if (update.username() != null) this.username=update.username();
        if (update.fechaCreacion() != null) this.fechaDeCreacion=update.fechaCreacion();



    }


    public void desactivarTopico() {
        this.activo = false;
    }
}
