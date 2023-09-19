package com.yummy.repository;

import com.yummy.dto.LoginLogDto;
import com.yummy.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepository extends JpaRepository<LoginLog, Long>  {
}
