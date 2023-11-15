package com.bankduck.AdministrarCliente.common;

import com.bankduck.AdministrarCliente.dto.ClienteRequest;
import com.bankduck.AdministrarCliente.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteRequestMapper {
    Cliente ClienteRequestToCliente(ClienteRequest source);
}
