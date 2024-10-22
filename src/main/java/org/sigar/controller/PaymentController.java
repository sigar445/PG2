package org.sigar.controller;

import jakarta.transaction.Transaction;
import org.sigar.dto.DTOConverter;
import org.sigar.dto.PaymentTransactionResponseDTO;
import org.sigar.dto.RentalContractResponseDTO;
import org.sigar.model.PaymentTransaction;
import org.sigar.model.RentalContract;
import org.sigar.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/PG2/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/rentalContract")
    public ResponseEntity<RentalContractResponseDTO> addRentalContract(@RequestBody RentalContract rentalContract){
        logger.info("Adding new Rental Contract");
        RentalContractResponseDTO rentalContractResponseDTO = paymentService.addRentalContract(rentalContract);
        return ResponseEntity.ok(rentalContractResponseDTO);
    }
    @PostMapping("/transaction")
    public ResponseEntity<PaymentTransactionResponseDTO> addPaymentTransaction(@RequestBody PaymentTransaction paymentTransaction){
        logger.info("Adding new Payment Transaction");
        PaymentTransactionResponseDTO transactionResponseDTO = paymentService.addPaymentTransaction(paymentTransaction);
        return ResponseEntity.ok(transactionResponseDTO);
    }
}
