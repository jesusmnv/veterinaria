package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.mapstruct.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InvoiceMapper {

    // Método estático para construir y configurar el ObjectMapper
    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registrar el módulo JavaTimeModule
        return objectMapper;
    }

    InvoiceDTO toDTO(Invoice model);

    List<InvoiceDTO> toDTO(List<Invoice> model);

    @Mapping(target = "id", ignore = true)
    Invoice toModel(CreateInvoiceDTO dto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget Invoice invoice, UpdateInvoiceDTO data);

}