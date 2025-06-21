package org.SplitLedger.repository;

import org.SplitLedger.entity.Reminder;
import org.SplitLedger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    List<Reminder> findAllByToUser(User toUser);
}
