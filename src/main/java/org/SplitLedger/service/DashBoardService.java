package org.SplitLedger.service;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.dashboard.*;
import org.SplitLedger.entity.*;
import org.SplitLedger.entity.enums.Status;
import org.SplitLedger.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DebtRepository debtRepository;
    private final ReminderRepository reminderRepository;
    private final GroupSplitMemberRepository groupSplitMemberRepository;
    private final UserRepository userRepository;

    public DashBoardDTO getDashboard(String username) {
        User user = userRepository.findByUsername(username);

        List<Debt> borrowedDebts = debtRepository.findAllByBorrower(user);
        List<Debt> lentDebts = debtRepository.findAllByLender(user);

        BigDecimal totalOwed = calculateTotalAmount(borrowedDebts);

        List<DebtDashDTO> debts = borrowedDebts.stream()
                .map(debt -> new DebtDashDTO(debt.getId(), debt.getLender().getId(), debt.getAmount(), debt.getStatus()))
                .collect(Collectors.toList());

        BigDecimal totalLent = calculateTotalAmount(lentDebts);

        List<DebtorDashDTO> debtors = lentDebts.stream()
                .map(debt -> new DebtorDashDTO(debt.getId(), debt.getBorrower().getId(), debt.getAmount(), debt.getStatus()))
                .collect(Collectors.toList());

        List<ReminderDashDTO> reminders = reminderRepository.findAllByToUser(user).stream()
                .map(reminder -> new ReminderDashDTO(reminder.getMessage(), reminder.getStatus()))
                .collect(Collectors.toList());

        List<GroupDashDTO> groups = new ArrayList<>();
        for (GroupSplitMember member : groupSplitMemberRepository.findByUser(user)) {
            GroupDashDTO groupDTO = new GroupDashDTO();
            groupDTO.setGroupTitle(member.getGroupSplit().getTitle());
            groupDTO.setPaid(member.isPaid());
            groupDTO.setMyShare(member.getAmountOwed());

            if (!member.isPaid()) {
                totalOwed = totalOwed.add(member.getAmountOwed());
            }

            groups.add(groupDTO);
        }

        return new DashBoardDTO(
                user.getUsername(),
                user.getEmail(),
                totalOwed,
                totalLent,
                debts,
                debtors,
                reminders,
                groups
        );
    }

    public DebtListResponse getDebts(String username) {
        User user = userRepository.findByUsername(username);

        List<Debt> borrowedDebts = debtRepository.findAllByBorrower(user);

        List<DebtDTO> debts = borrowedDebts.stream()
                .map(debt -> new DebtDTO(
                        debt.getId(),
                        debt.getLender().getUsername(),
                        debt.getAmount(),
                        debt.getCurrency(),
                        debt.getStatus(),
                        debt.getCreatedAt(),
                        debt.getDescription()
                ))
                .collect(Collectors.toList());

        BigDecimal totalOwed = calculateTotalAmount(borrowedDebts);
        long totalActiveDebts = debtRepository.countByBorrowerAndStatusNot(user, Status.PAID);

        return new DebtListResponse(totalOwed, totalActiveDebts, debts);
    }


    public DebtorListResponse getDebtors(String username) {
        User user = userRepository.findByUsername(username);

        List<Debt> lendedDebts = debtRepository.findAllByLender(user);

        List<DebtorDTO> debtors = lendedDebts.stream()
                .map(debtor -> new DebtorDTO(
                        debtor.getId(),
                        debtor.getBorrower().getUsername(),
                        debtor.getAmount(),
                        debtor.getCurrency(),
                        debtor.getStatus(),
                        debtor.getCreatedAt(),
                        debtor.getDescription()
                ))
                .collect(Collectors.toList());

        BigDecimal totalLent = calculateTotalAmount(lendedDebts);
        long totalActiveLend = debtRepository.countByLenderAndStatusNot(user, Status.PAID);

        return new DebtorListResponse(totalLent, totalActiveLend, debtors);
    }

    private BigDecimal calculateTotalAmount(List<Debt> debts) {
        return debts.stream()
                .map(Debt::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
