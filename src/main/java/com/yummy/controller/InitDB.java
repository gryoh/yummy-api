package com.yummy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.yummy.entity.MbrBase;
import com.yummy.entity.RcpBase;
import com.yummy.entity.RcpStuff;
import com.yummy.entity.StuffBase;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitDBService initDBService;

    @PostConstruct
    public void init(){

        initDBService.init();
    }

    @Component
    static class InitDBService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            // 회원 추가
            MbrBase mbrBase = new MbrBase(
                    "hungry",
                    "오형준",
                    "1234",
                    "01011112222",
                    "hungry6459@naver.com",
                    "19930109",
                    "10",
                    "1",
                    null,
                    null
            );
            em.persist(mbrBase);

            // todo 재료 추가(최소 7가지)
            StuffBase stuffBase1 = new StuffBase("양파", "매움", "Y", "10cal", "나트륨 10g", "당분 1g", "이미지파일경로1", "이미지파일명1");
            StuffBase stuffBase2 = new StuffBase("당근", "달달", "Y", "11cal", "나트륨 11g", "당분 1g", "이미지파일경로2", "이미지파일명2");
            StuffBase stuffBase3 = new StuffBase("피망", "매움", "Y", "12cal", "나트륨 12g", "당분 2g", "이미지파일경로3", "이미지파일명3");
            StuffBase stuffBase4 = new StuffBase("상추", "졸림", "Y", "13cal", "나트륨 13g", "당분 3g", "이미지파일경로4", "이미지파일명4");
            StuffBase stuffBase5 = new StuffBase("깻잎", "향남", "Y", "14cal", "나트륨 14g", "당분 4g", "이미지파일경로5", "이미지파일명5");
            StuffBase stuffBase6 = new StuffBase("대파", "맵다", "Y", "15cal", "나트륨 15g", "당분 5g", "이미지파일경로6", "이미지파일명6");
            StuffBase stuffBase7 = new StuffBase("마늘", "맛있", "Y", "16cal", "나트륨 16g", "당분 6g", "이미지파일경로7", "이미지파일명7");

            em.persist(stuffBase1);
            em.persist(stuffBase2);
            em.persist(stuffBase3);
            em.persist(stuffBase4);
            em.persist(stuffBase5);
            em.persist(stuffBase6);
            em.persist(stuffBase7);

            // todo 레시피 추가(최소 7개)
            RcpBase rcpBase1 = new RcpBase("양파볶음", "매움함", "Y");
            RcpBase rcpBase2 = new RcpBase("당근볶음", "달달함", "Y");
            RcpBase rcpBase3 = new RcpBase("피망볶음", "매움함", "Y");
            RcpBase rcpBase4 = new RcpBase("상추볶음", "졸림함", "Y");
            RcpBase rcpBase5 = new RcpBase("깻잎볶음", "향남함", "Y");
            RcpBase rcpBase6 = new RcpBase("대파볶음", "맵다함", "Y");
            RcpBase rcpBase7 = new RcpBase("마늘볶음", "맛있함", "Y");

            em.persist(rcpBase1);
            em.persist(rcpBase2);
            em.persist(rcpBase3);
            em.persist(rcpBase4);
            em.persist(rcpBase5);
            em.persist(rcpBase6);
            em.persist(rcpBase7);

            // todo 레시피안에 들어가는 재료 매핑테이블에 데이터 추가
            RcpStuff rcpStuff1 = new RcpStuff("11", 1, rcpBase1, stuffBase1);
            RcpStuff rcpStuff2 = new RcpStuff("12", 2, rcpBase2, stuffBase2);
            RcpStuff rcpStuff3 = new RcpStuff("13", 3, rcpBase3, stuffBase3);
            RcpStuff rcpStuff4 = new RcpStuff("14", 4, rcpBase4, stuffBase4);
            RcpStuff rcpStuff5 = new RcpStuff("15", 5, rcpBase5, stuffBase5);
            RcpStuff rcpStuff6 = new RcpStuff("16", 6, rcpBase6, stuffBase6);
            RcpStuff rcpStuff7 = new RcpStuff("17", 7, rcpBase7, stuffBase7);

            em.persist(rcpStuff1);
            em.persist(rcpStuff2);
            em.persist(rcpStuff3);
            em.persist(rcpStuff4);
            em.persist(rcpStuff5);
            em.persist(rcpStuff6);
            em.persist(rcpStuff7);

            // todo 레시피번호 목록을 가지고 식단에 레시피추가
        }
    }
}
