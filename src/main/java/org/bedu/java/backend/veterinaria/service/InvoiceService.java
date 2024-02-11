package org.bedu.java.backend.veterinaria.service;

import lombok.extern.slf4j.Slf4j;
import org.bedu.java.backend.veterinaria.dto.invoice.CreateInvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.InvoiceDTO;
import org.bedu.java.backend.veterinaria.dto.invoice.UpdateInvoiceDTO;
import org.bedu.java.backend.veterinaria.exception.InvoiceNotFoundException;
import org.bedu.java.backend.veterinaria.mapper.InvoiceMapper;
import org.bedu.java.backend.veterinaria.model.Invoice;
import org.bedu.java.backend.veterinaria.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
@Slf4j
public class InvoiceService {
    private InvoiceRepository repository;
    private InvoiceMapper mapper;

    @Autowired
    public InvoiceService(InvoiceRepository repository, InvoiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<InvoiceDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }

    public Optional<InvoiceDTO> findById(Long id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    public InvoiceDTO save(CreateInvoiceDTO data) {
        Invoice temp = mapper.toModel(data);
        temp.setVat(data.getSubtotal() * 0.16F);
        temp.setTotal(temp.getVat() + data.getSubtotal());

        Invoice entity = repository.save(temp);
        return mapper.toDTO(entity);
    }

    public void update(Long invoiceId, UpdateInvoiceDTO data) throws InvoiceNotFoundException {
        Optional<Invoice> result = repository.findById(invoiceId);

        if (!result.isPresent()) {
            throw new InvoiceNotFoundException(invoiceId);
        }

        Invoice invoice = result.get();

        UpdateInvoiceDTO temp = data;
        log.error("Datos: " + temp);
        temp.setSubtotal(data.getSubtotal());
        temp.setVat(temp.getSubtotal() * 0.16F);
        float total = temp.getVat() + temp.getSubtotal();
        temp.setTotal(total);

        log.error("============TOTAL===============: " + total);
        mapper.update(invoice, temp);
        repository.save(invoice);
    }

    public void deleteById(Long id) throws InvoiceNotFoundException {
        Optional<Invoice> result = repository.findById(id);

        if (!result.isPresent()) {
            throw new InvoiceNotFoundException(id);
        }

        repository.deleteById(id);
    }

}