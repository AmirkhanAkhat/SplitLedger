package org.SplitLedger.repository;


import org.SplitLedger.entity.Debt;
import org.SplitLedger.entity.User;
import org.SplitLedger.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    // Я должен другим
    List<Debt> findAllByBorrower(User borrower);

    // Другие должны мне
    List<Debt> findAllByLender(User lender);

    long countByBorrowerAndStatusNot(User borrower, Status status);

    long countByLenderAndStatusNot(User lender, Status status);
}
