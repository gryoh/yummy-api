package com.yummy.repository;

import com.yummy.entity.MbrBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MbrBase, Long> {

}
