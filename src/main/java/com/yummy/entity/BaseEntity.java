package com.yummy.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false, name = "SYS_REG_TIME")
    protected LocalDateTime sysRegTime;

    @Column(updatable = false, name = "SYS_REG_ID")
    protected String sysRegId;

    @Column(name = "SYS_MOD_TIME")
    protected LocalDateTime sysModTime;

    @Column(name = "SYS_MOD_ID")
    protected String sysModId;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        sysRegTime = now;
        sysModTime = now;
    }

    @PreUpdate
    public void preUpdate(){
        //todo 쿠키나 다른 방법을 통해 [sysModId]에 [사용자 ID]를 넣을 수 있도록 수정
        sysModId = "todo";
        sysModTime = LocalDateTime.now();
    }
}
