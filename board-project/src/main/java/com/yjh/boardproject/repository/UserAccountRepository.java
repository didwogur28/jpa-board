package com.yjh.boardproject.repository;

import com.yjh.boardproject.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository  extends JpaRepository<UserAccount, String> {
}
