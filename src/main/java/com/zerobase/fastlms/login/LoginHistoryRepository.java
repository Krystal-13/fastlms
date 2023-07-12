package com.zerobase.fastlms.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistoryRepository extends JpaRepository<LoginHistory, String> {

}
