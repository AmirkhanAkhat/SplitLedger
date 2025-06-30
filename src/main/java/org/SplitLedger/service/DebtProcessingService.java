package org.SplitLedger.service;


import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.debts.DResponse;
import org.SplitLedger.dto.debts.DebtRequest;
import org.SplitLedger.dto.debts.DebtorRequest;
import org.SplitLedger.entity.Debt;
import org.SplitLedger.entity.User;
import org.SplitLedger.entity.enums.Status;
import org.SplitLedger.repository.DebtRepository;
import org.SplitLedger.repository.GroupSplitMemberRepository;
import org.SplitLedger.repository.ReminderRepository;
import org.SplitLedger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebtProcessingService {


    private final DebtRepository debtRepository;
    private final ReminderRepository reminderRepository;
    private final GroupSplitMemberRepository groupSplitMemberRepository;
    private final UserRepository userRepository;

    public DResponse createDebtor(String lenderUsername, DebtorRequest debtorRequest) {

        Optional<User> borrower = userRepository.findByEmail(debtorRequest.getBorrowerEmail());
        if (borrower.isEmpty()) {
            return new DResponse(false, "NO SUCH USER");
        }

        User lender = userRepository.findByUsername(lenderUsername);
        if (lender == null) {
            return new DResponse(false, "NO SUCH LENDER");
        }

        Debt debt = new Debt();

        debt.setAmount(debtorRequest.getAmount());
        debt.setCurrency(debtorRequest.getCurrency());
        debt.setStatus(Status.APPROVED);
        debt.setLender(lender);
        debt.setBorrower(borrower.get());
        debt.setDescription(debtorRequest.getComment());

        debtRepository.save(debt);

        return new DResponse(true);
    }


    public DResponse createDebt(String borrowerUsername, DebtRequest debtRequest) {
        Optional<User> borrower = userRepository.findByEmail(borrowerUsername);
        if (borrower.isEmpty()) {
            return new DResponse(false, "NO SUCH USER");
        }

        User lender = userRepository.findByUsername(debtRequest.getLenderEmail());
        if (lender == null) {
            return new DResponse(false, "NO SUCH LENDER");
        }

        Debt debt = new Debt();

        debt.setBorrower(borrower.get());
        debt.setLender(lender);
        debt.setCurrency(debtRequest.getCurrency());
        debt.setAmount(debtRequest.getAmount());
        debt.setDescription(debtRequest.getComment());
        debt.setStatus(Status.REQUESTED);


        debtRepository.save(debt);


        return new DResponse(true);
    }
}
