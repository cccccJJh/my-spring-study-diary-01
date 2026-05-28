package com.study.my_spring_study_diary.repository;

//저장소 (Map 기반)

import com.study.my_spring_study_diary.entity.Category;
import com.study.my_spring_study_diary.entity.StudyLog;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 학습 일지 저장소
 *
 * @Repository 어노테이션 설명:
 * - 이 클래스를 Spring Bean으로 등록합니다
 * - 데이터 접근 계층임을 명시합니다
 * - 데이터 접근 관련 예외를 Spring의 DataAccessException으로 변환해줍니다
 *
 * 실제 프로젝트에서는 JPA, MyBatis 등을 사용하지만,
 * 이번 강의에서는 Map을 사용하여 데이터를 저장합니다.
 */
@Repository  // ⭐ Spring Bean으로 등록!
public class StudyLogRepository {

    //데이터 저장소 (실제 db 대신 MAP 사용)
    private final Map<Long, StudyLog> database = new HashMap<>();

    //ID 자동 증가를 위한 시퀀스
    private final AtomicLong sequence = new AtomicLong();


    /**
     * @PostConstruct: Bean 생성 및 의존성 주입 완료 후 실행
     * 초기 데이터 설정, 리소스 초기화 등에 활용
     */
    @PostConstruct
    public void init(){
        System.out.println("🚀 StudyLogRepository 초기화 완료!");
        System.out.println("📦 데이터베이스(Map) 준비 완료!");

        // 테스트용 초기 데이터 추가 (선택사항)
        // initSampleData();
    }

    /**
     * @PreDestroy: Bean 소멸 전 실행
     * 리소스 정리, 연결 해제 등에 활용
     */
    @PreDestroy
    public void destory(){
        System.out.println("🔚 StudyLogRepository 종료!");
        System.out.println("🗑️ 저장된 데이터 개수: " + database.size());

    }

    /**
     * 학습 일지 저장
     * @param studyLog 저장할 학습 일지
     * @return 저장된 학습 일지 (ID 포함)
     */
    public StudyLog save(StudyLog studyLog){
        //ID가 없으면 새로운 ID 부여
        if(studyLog.getId() == null){
            studyLog.setId(sequence.getAndIncrement());
        }

        //MAP에 저장
        database.put(studyLog.getId(), studyLog);

        return studyLog;
    }

    //학습일지 조회 (최신순 정렬)
    public List<StudyLog> findAll(){
        return database.values().stream()
                .sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    //ID로 학습일지 조회
    public Optional<StudyLog> findById(Long id){
        return Optional.ofNullable(database.get(id)); // 값이 잇을수도잇고 없을수도 있음..
    }

    //날짜로 학습일지 조회
    public List<StudyLog> findByStudyDate(LocalDate date){

        return database.values().stream()
                .filter(log -> log.getStudyDate().equals(date))
                .sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    //카테고리별 조회
    public List<StudyLog> findByCategory(Category category){
        return database.values().stream()
                .filter(log -> log.getCategory().equals(category))
                .sorted((a,b)->b.getCreatedAt().compareTo(a.getCreatedAt()))
                .collect(Collectors.toList());
    }

    //저장된 데이터 개수 조회
    public long count(){
        return database.size();
    }
}
