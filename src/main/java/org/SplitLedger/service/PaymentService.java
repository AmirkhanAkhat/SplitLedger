package org.SplitLedger.service;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.APIResponse;
import org.SplitLedger.dto.payment.PaymentRequest;
import org.SplitLedger.entity.Debt;
import org.SplitLedger.entity.Payment;
import org.SplitLedger.entity.User;
import org.SplitLedger.entity.enums.Status;
import org.SplitLedger.repository.DebtRepository;
import org.SplitLedger.repository.PaymentRepository;
import org.SplitLedger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final DebtRepository debtRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;


    public APIResponse processPayment(String fromUsername, PaymentRequest paymentRequest) {
        User fromUser = userRepository.findByUsername(fromUsername);

        if (fromUser == null) {
            return new APIResponse(false, "User not found");
        }

        Debt debt = debtRepository.findById(paymentRequest.getDebtId()).orElse(null);

        if (debt == null) {
            return new APIResponse(false, "Debt not found");
        }

        if (!debt.getBorrower().getId().equals(fromUser.getId())) {
            return new APIResponse(false, "Not authorized to pay this debt");
        }


        if (paymentRequest.getAmount().compareTo(debt.getAmount()) >= 0) {
            debt.setStatus(Status.PAID);
            debt.setPaidAt(LocalDateTime.now());

        }


        Payment payment = new Payment();
        payment.setDebt(debt);
        payment.setAmount(paymentRequest.getAmount());
        payment.setMethod(paymentRequest.getMethod());
        payment.setCurrency(debt.getCurrency());
        payment.setFromUser(fromUser);
        payment.setToUser(debt.getLender());
        payment.setDescription(paymentRequest.getComment());

        paymentRepository.save(payment);
        debtRepository.save(debt);

        return new APIResponse(true, "Payment processed");
    }
}
