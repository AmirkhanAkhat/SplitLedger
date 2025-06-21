package org.SplitLedger.repository;

import org.SplitLedger.entity.GroupSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupSplitRepository extends JpaRepository<GroupSplit, Long> {

}
