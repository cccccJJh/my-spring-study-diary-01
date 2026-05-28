package com.study.my_spring_study_diary.global.common;


import lombok.Getter;

// 공통 API 응답 래퍼
@Getter
public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // 성공 시 데이터를 담아 반환하는 정적 팩토리 메서드
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "요청이 성공적으로 처리되었습니다.", data);
    }

    // 성공 시 메시지와 데이터를 함께 담아 반환하는 버전 (필요 시)
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    // 실패 시 응답 (데이터가 없으므로 Void 처리)
    public static ApiResponse<Void> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }

    // 에러 코드와 메시지를 함께 담는 버전
    public static ApiResponse<Void> error(String errorCode, String message) {
        return new ApiResponse<>(false, errorCode + ": " + message, null);
    }

}
