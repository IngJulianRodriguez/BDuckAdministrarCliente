package com.bankduck.AdministrarCliente.common;

import com.bankduck.AdministrarCliente.dto.ClienteResponse;
import com.bankduck.AdministrarCliente.entities.Cliente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClienteResponseMapper {
    ClienteResponse ClienteToClienteResponse(Cliente source);
    List<ClienteResponse> ClienteListToClienteResponseList(List<Cliente> source);
}
