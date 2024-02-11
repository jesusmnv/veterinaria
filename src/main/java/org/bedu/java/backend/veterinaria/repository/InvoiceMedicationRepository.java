package org.bedu.java.backend.veterinaria.repository;

import java.util.List;
import org.bedu.java.backend.veterinaria.model.InvoiceMedication;
import org.bedu.java.backend.veterinaria.model.InvoiceMedicationKey;
import org.bedu.java.backend.veterinaria.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceMedicationRepository extends JpaRepository<InvoiceMedication, InvoiceMedicationKey> {

    @Query("SELECT f.medication FROM InvoiceMedication f WHERE f.invoice.id = :invoiceId")
    List<Medication> findMedicationsByInvoice(Long invoiceId);

}