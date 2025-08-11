package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {
    @Test
    @DisplayName("등록")
    void t1(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out).contains("명령) ");
        assertThat(out).contains("명언 : ");
        assertThat(out).contains("작가 : ");

    }

    @Test
    @DisplayName("등록 시 명언 번호 출력")
    void t2(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                """);

        assertThat(out).contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("등록할때마다 생성되는 명언번호가 증가")
    void t3(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                """);


        assertThat(out).contains("2번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록 출력")
    void t4(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                현재를 사랑하라.
                작자미상
                목록
                """);


        assertThat(out).contains("번호 / 작가 / 명언");
        assertThat(out).contains("----------------------");
        assertThat(out).contains("2 / 작자미상 / 현재를 사랑하라.");
        assertThat(out).contains("1 / 작자미상 / 현재를 사랑하라.");
    }
}
