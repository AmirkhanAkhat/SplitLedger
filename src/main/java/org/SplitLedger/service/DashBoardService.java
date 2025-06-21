package org.SplitLedger.service;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.*;
import org.SplitLedger.entity.*;
import org.SplitLedger.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashBoardService {

    private final DebtRepository debtRepository;
    private final ReminderRepository reminderRepository;
    private final GroupSplitRepository groupSplitRepository;
    private final GroupSplitMemberRepository groupSplitMemberRepository;
    private final UserRepository userRepository;

    public DashBoardDTO getDashboard(String username) {
        User user = userRepository.findByUsername(username);

        BigDecimal totalOwed = BigDecimal.ZERO;
        BigDecimal totalLent = BigDecimal.ZERO;

        List<DebtDTO> debts = new ArrayList<>();
        for (Debt debt : debtRepository.findAllByBorrower(user)) {
            debts.add(new DebtDTO(debt.getId(), debt.getLender().getId(), debt.getAmount(), debt.getStatus()));
            totalOwed = totalOwed.add(debt.getAmount());
        }

        List<DebtorDTO> debtors = new ArrayList<>();
        for (Debt debt : debtRepository.findAllByLender(user)) {
            debtors.add(new DebtorDTO(debt.getId(), debt.getBorrower().getId(), debt.getAmount(), debt.getStatus()));
            totalLent = totalLent.add(debt.getAmount());
        }

        List<ReminderDTO> reminders = new ArrayList<>();
        for (Reminder reminder : reminderRepository.findAllByToUser(user)) {
            reminders.add(new ReminderDTO(reminder.getMessage(), reminder.getStatus()));
        }

        List<GroupDTO> groups = new ArrayList<>();
        for (GroupSplitMember member : groupSplitMemberRepository.findByUser(user)) {
            GroupDTO groupDTO = new GroupDTO();
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
}
