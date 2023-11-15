package com.bankduck.AdministrarCliente.controller;

import com.bankduck.AdministrarCliente.dto.ClienteRequest;
import com.bankduck.AdministrarCliente.dto.ClienteResponse;
import com.bankduck.AdministrarCliente.dto.CredencialesClienteRequest;
import com.bankduck.AdministrarCliente.entities.Cliente;
import com.bankduck.AdministrarCliente.entities.ServiciosCliente;
import com.bankduck.AdministrarCliente.service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "Api Administrar Cliente")
@RestController
@RequestMapping("/administrarCliente")
public class ClienteRestController {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/crearClienteEnProceso")
    public ResponseEntity<?> crearClienteEnProceso(@RequestBody ClienteRequest input) {
        ClienteResponse clienteResponse = clienteService.CrearCliente(input);
        return ResponseEntity.ok(clienteResponse);
    }

    @PostMapping("/asignarServicioEnProceso/{clienteId}/{servicioId}")
    public boolean asignarServicioEnProceso(@PathVariable Long clienteId, @PathVariable  Long servicioId){
        return clienteService.asignarServicio(clienteId,servicioId);

    }

    @PutMapping
    public ResponseEntity<?> generarCredenciales(@RequestBody CredencialesClienteRequest input){
        String usuario = clienteService.generarCredenciales(input);
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredencialesClienteRequest input){

        if(clienteService.login(input)){
            return ResponseEntity.ok(input.getCedula());
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
