package com.api_clientes.app.models.Service;

import com.api_clientes.app.models.Clientes;
import com.api_clientes.app.models.Dao.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteServiceImp implements  ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Clientes> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional
    @Override
    public Clientes save(Clientes cliente) {

        return clienteRepository.save(cliente);

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Clientes> finById(Long Id) {
        return clienteRepository.findById(Id);
    }

    @Transactional
    @Override
    public void  DeleteById(Long Id) {
      clienteRepository.deleteById(Id);
    }
}
