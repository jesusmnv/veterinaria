package org.bedu.java.backend.veterinaria.mapper;

import org.bedu.java.backend.veterinaria.model.InvoiceMedication;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InvoiceMedicationMapper {

    @Mapping(source = "invoiceId", target = "id.invoiceId")
    @Mapping(source = "invoiceId", target = "invoice.id")
    @Mapping(source = "medicationId", target = "id.medicationId")
    @Mapping(source = "medicationId", target = "medication.id")
    InvoiceMedication toModel(Long invoiceId, Long medicationId, float price, int quantity);
}