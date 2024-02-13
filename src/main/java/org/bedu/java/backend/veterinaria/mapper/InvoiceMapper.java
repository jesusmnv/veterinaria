package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InvoiceMapper {

    InvoiceDTO toDTO(Invoice model);

    List<InvoiceDTO> toDTO(List<Invoice> model);

    @Mapping(target = "id", ignore = true)
    Invoice toModel(CreateInvoiceDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "issuanceDate", source = "issuanceDateU")
    @Mapping(target = "subtotal", source = "subtotalU")
    @Mapping(target = "vat", source = "vatU")
    @Mapping(target = "total", source = "totalU")
    @Mapping(target = "clientRFC", source = "clientRFCU")
    @Mapping(target = "legalName", source = "legalNameU")
    @Mapping(target = "owner", source = "ownerU")
    void update(@MappingTarget Invoice invoice, UpdateInvoiceDTO data);

}