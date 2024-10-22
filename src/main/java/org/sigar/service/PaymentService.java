package org.sigar.service;

import org.sigar.Constants.enums.TransactionType;
import org.sigar.dto.DTOConverter;
import org.sigar.dto.PaymentTransactionResponseDTO;
import org.sigar.dto.RentalContractResponseDTO;
import org.sigar.model.PaymentTransaction;
import org.sigar.model.RentalContract;
import org.sigar.repo.PaymentTransactionRepository;
import org.sigar.repo.RentalContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentTransactionRepository transactionRepository;
    private final RentalContractRepository contractRepository;

    @Autowired
    public PaymentService(PaymentTransactionRepository transactionRepository, RentalContractRepository contractRepository) {
        this.transactionRepository = transactionRepository;
        this.contractRepository = contractRepository;
    }

//    public List<PaymentTransaction> getAllTransactions() {
//        return transactionRepository.findAll();
//    }
    public RentalContractResponseDTO addRentalContract(RentalContract rentalContract){
        return DTOConverter.convertToRentalContractResponseDTO(contractRepository.save(rentalContract));
    }
    public PaymentTransactionResponseDTO addPaymentTransaction(PaymentTransaction transaction){
        return DTOConverter.convertToPaymentTransactionResponseDTO(transactionRepository.save(transaction));
    }
//    // Method to process  payment
//    public void processPayment(Long rentalContractId, TransactionType transactionType, int amountPaid) {
//        RentalContract rentalContract = contractRepository.findById(rentalContractId)
//                .orElseThrow(() -> new IllegalArgumentException("Rental contract not found"));
//
//        if (rentalContract.isRentDue()) {
//            // Update the rent due date
//            rentalContract.updateRentDueDate();
//
//            // Create a new rent payment transaction
//            PaymentTransaction transaction = PaymentTransaction.createPaymentTransaction(rentalContract, amountPaid,transactionType,"Rent payment for month");
//            transactionRepository.save(transaction);
//
//            // Save the updated LeaseAgreement
//            contractRepository.save(rentalContract);
//        } else {
//            throw new IllegalStateException("Rent is not due yet.");
//        }
//    }
}
//}
//// Process rent payment
//public Transaction processRentPayment(Long rentalContractId, Integer amount) {
//    RentalContract rentalContract = rentalContractRepository.findById(rentalContractId)
//            .orElseThrow(() -> new ResourceNotFoundException("Rental contract not found"));
//
//    // Update due date after payment
//    rentalContract.updateRentDueDate();
//
//    // Record the transaction
//    Transaction transaction = new Transaction();
//    transaction.setRentalContract(rentalContract);
//    transaction.setAmount(amount);
//    transaction.setTransactionType(TransactionType.RENT);
//    transaction.setTransactionDate(LocalDate.now());
//
//    rentalContractRepository.save(rentalContract);
//    return transactionRepository.save(transaction);
//}
//
//// Process utility payment
//public Transaction processUtilityPayment(Long rentalContractId, Integer amount) {
//    // Similar to rent payment but with TransactionType.UTILITY
//    return createTransaction(rentalContractId, amount, TransactionType.UTILITY);
//}

//// Process advance payment
//public Transaction processAdvancePayment(Long rentalContractId, Integer amount) {
//    // Similar to rent payment but with TransactionType.ADVANCE
//    return createTransaction(rentalContractId, amount, TransactionType.ADVANCE);
//}
//
//// Process other charges (e.g., maintenance)
//public Transaction processOtherCharges(Long rentalContractId, Integer amount, String notes) {
//    Transaction transaction = createTransaction(rentalContractId, amount, TransactionType.OTHER);
//    transaction.setNotes(notes);
//    return transactionRepository.save(transaction);
//}
//
//// Helper method to create transaction
//private Transaction createTransaction(Long rentalContractId, Integer amount, TransactionType type) {
//    RentalContract rentalContract = rentalContractRepository.findById(rentalContractId)
//            .orElseThrow(() -> new ResourceNotFoundException("Rental contract not found"));
//
//    Transaction transaction = new Transaction();
//    transaction.setRentalContract(rentalContract);
//    transaction.setAmount(amount);
//    transaction.setTransactionType(type);
//    transaction.setTransactionDate(LocalDate.now());
//
//    return transactionRepository.save(transaction);
//}
