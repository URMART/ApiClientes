package com.api_clientes.app.models;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "clientes")
public class Clientes {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellido;
    private String dir;
    private int edad;


    public Clientes() {

    }

    public Clientes(long id, String nombre, String apellido, String dir, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dir = dir;
        this.edad = edad;
    }

    public Clientes(String nombre, String apellido, String dir, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dir = dir;
        this.edad = edad;
    }

}
