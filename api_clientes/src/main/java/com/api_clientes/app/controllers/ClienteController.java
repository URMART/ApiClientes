package com.api_clientes.app.controllers;

import com.api_clientes.app.models.Clientes;
import com.api_clientes.app.models.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Clientes> obtenerClientes(){
        return clienteService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> obtenerClientePorId(@PathVariable("id") Long id){

        Optional<Clientes> cliente = clienteService.finById(id);
        if(cliente.isPresent()){
            Map<String, Object> response = new HashMap<>();
            response.put("menssage", "Cliente Encontrado");
            response.put("cliente", cliente);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "Cliente No Existe");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> crearClientes(@RequestBody Clientes cliente){

        clienteService.save(cliente);
        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "Cliente Creado");
        response.put("cliente", cliente);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    @PutMapping("{id}")
    public Object actualizarClientePorId(@PathVariable("id") Long id , @RequestBody Clientes clienteBody){

        Optional<Clientes> cliente = clienteService.finById(id);

        if(cliente.isPresent()){
            return cliente
                    .map(clienteGuardado -> {
                        clienteGuardado.setNombre(clienteBody.getNombre());
                        clienteGuardado.setApellido(clienteBody.getApellido());
                        clienteGuardado.setDir(clienteBody.getDir());
                        clienteGuardado.setEdad(clienteBody.getEdad());
                        Clientes clienteActualizado = clienteService.save(clienteGuardado);
                        return new ResponseEntity<>(clienteActualizado,HttpStatus.OK);
                    });


        }

        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "El  Cliente No Se Existe");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("{id}")
    public Object eliminarClientePorId(@PathVariable("id") Long id ){

        Optional<Clientes> cliente = clienteService.finById(id);

        if(cliente.isPresent()){

            clienteService.DeleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("menssage", "Eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "El  Cliente No Se Existe");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }








}
