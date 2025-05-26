package org.SplitLedger.repository;


import org.SplitLedger.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    String getByEmail(String email);

    boolean existsByEmail(String email);
}
