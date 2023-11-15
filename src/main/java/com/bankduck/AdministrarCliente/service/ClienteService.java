package com.bankduck.AdministrarCliente.service;

import com.bankduck.AdministrarCliente.common.ClienteRequestMapper;
import com.bankduck.AdministrarCliente.common.ClienteResponseMapper;
import com.bankduck.AdministrarCliente.dto.ClienteRequest;
import com.bankduck.AdministrarCliente.dto.ClienteResponse;
import com.bankduck.AdministrarCliente.dto.CredencialesClienteRequest;
import com.bankduck.AdministrarCliente.entities.Cliente;
import com.bankduck.AdministrarCliente.entities.Servicio;
import com.bankduck.AdministrarCliente.entities.ServiciosCliente;
import com.bankduck.AdministrarCliente.repository.ClienteRepository;
import com.bankduck.AdministrarCliente.repository.ServicioRepository;
import com.bankduck.AdministrarCliente.repository.ServiciosClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteResponseMapper clienteResponseMapper;
    @Autowired
    ClienteRequestMapper clienteRequestMapper;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ServiciosClienteRepository serviciosClienteRepository;

    @Autowired
    ServicioRepository servicioRepository;

    public ClienteResponse CrearCliente(ClienteRequest clienteRequest){
        Cliente clienteRequestToCliente = clienteRequestMapper.ClienteRequestToCliente(clienteRequest);
        clienteRequestToCliente.setEstado("En proceso");
        Cliente save = clienteRepository.save(clienteRequestToCliente);
        ClienteResponse ClienteToClienteResponse = clienteResponseMapper.ClienteToClienteResponse(save);
        return ClienteToClienteResponse;
    }

    public boolean asignarServicio(Long clienteId, Long servicioId){
        ServiciosCliente serviciosCliente = new ServiciosCliente();
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        Cliente getCliente = cliente.get();
        serviciosCliente.setCliente(getCliente);
        serviciosCliente.setServicioId(servicioId);
        serviciosCliente.setEstado("Inactivo");
        serviciosClienteRepository.save(serviciosCliente);
        return true;
    }
    public String generarCredenciales(CredencialesClienteRequest input){
        Optional<Cliente> OptionalCliente = clienteRepository.findById(input.getCedula());
        if(OptionalCliente.isPresent()){
            Cliente cliente = OptionalCliente.get();
            cliente.setUsuario(input.getCedula()+"");
            cliente.setContrasena(input.getContrasena());
            cliente.setEstado("Activo");
            clienteRepository.save(cliente);
            activarServicio(input.getCedula());
        }else{
            return "";
        }
        return clienteRepository.findById(input.getCedula()).get().getUsuario();
    }
    public boolean login(CredencialesClienteRequest input){
        Optional<Cliente> OptionalCliente = clienteRepository.findById(input.getCedula());
        if(OptionalCliente.isPresent()){
            Cliente cliente = OptionalCliente.get();
            return cliente.verificarContrasena(input.getContrasena());
        }
        return false;
    }
    public void activarServicio(Long cedula){
        Optional<ServiciosCliente> optionalServiciosCliente
                = serviciosClienteRepository.findByClienteCedulaAndEstado(cedula,"Inactivo");
        if(optionalServiciosCliente.isPresent()){
            ServiciosCliente serviciosCliente = optionalServiciosCliente.get();
            serviciosCliente.setEstado("Activo");
            serviciosClienteRepository.save(serviciosCliente);
        }
    }

}
