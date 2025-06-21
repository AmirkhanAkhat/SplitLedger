package org.SplitLedger.repository;

import org.SplitLedger.entity.GroupSplitMember;
import org.SplitLedger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupSplitMemberRepository extends JpaRepository<GroupSplitMember, Long> {
    List<GroupSplitMember> findByUser(User user);
}
