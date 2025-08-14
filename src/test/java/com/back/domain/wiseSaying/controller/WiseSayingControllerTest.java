package com.back.domain.wiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                과거에 집착하지 마라.
                작자미상
                목록
                """);


        assertThat(out).contains("번호 / 작가 / 명언");
        assertThat(out).contains("----------------------");
        assertThat(out).contains("2 / 작자미상 / 과거에 집착하지 마라.");
        assertThat(out).contains("1 / 작자미상 / 현재를 사랑하라.");
    }

    @Test
    @DisplayName("삭제?id=1")
    void t5(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                목록
                """);


        assertThat(out)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("2 / 작자미상 / 과거에 집착하지 마라.")
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.");

    }

    @Test
    @DisplayName("삭제?id=1 두 번 요청에 대한 처리")
    void t6(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라.
                작자미상
                삭제?id=1
                삭제?id=1
                """);


        assertThat(out)
                .contains("1번 명언이 삭제되었습니다.")
                .contains("1번 명언은 존재하지 않습니다.");

    }

    @Test
    @DisplayName("수정?id=3 없는 명언에 대한 수정 요청")
    void t7(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=3
                """);


        assertThat(out)
                .contains("3번 명언은 존재하지 않습니다.");

    }

    @Test
    @DisplayName("수정?id=1")
    void t8(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                현재와 자신을 사랑하라.
                홍길동
                목록
                """);


        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .contains("1 / 홍길동 / 현재와 자신을 사랑하라");

    }

    @Test
    @DisplayName("목록?keyword=이순신")
    void t9(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                이순신
                등록
                과거에 집착하지 마라
                이순신
                목록?&keyword=이순신
                """);


        assertThat(out)
                .contains("1 / 이순신 / 현재를 사랑하라.")
                .contains("2 / 이순신 / 과거에 집착하지 마라");

    }

    //step13
    @Test
    @DisplayName("목록?keywordType=content&keyword=작자")
    void t10(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라
                작자미상
                목록?keywordType=content&keyword=작자
                """);


        assertThat(out)
                .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
                .doesNotContain("2 / 작자미상 / 과거에 집착하지 마라");

    }

    @Test
    @DisplayName("목록?keywordType=author&keyword=작자")
    void t11(){

        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                과거에 집착하지 마라
                매용
                목록?keywordType=author&keyword=작자
                """);


        assertThat(out)
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .doesNotContain("2 / 매용 / 과거에 집착하지 마라");

    }

    //페이징 기능 구현 시작
    @Test
    @DisplayName("목록 : 한 페이지에 최신 명언 5개 출력")
    void t12(){

        //샘플 데이터 만들어줌
        String input = IntStream.rangeClosed(1,10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num,num))
                .collect(Collectors.joining("\n"));//문자열 여러개 나왔을때 합쳐줌

        input += "목록\n";

        String out = AppTestRunner.run(input);


        assertThat(out)
                .contains("10 / 작가 10 / 명언 10")
                .contains("6 / 작가 6 / 명언 6")
                .doesNotContain("1 / 작가 1 / 명언 1");

    }

    @Test
    @DisplayName("목록?page=2")
    void t13(){

        //샘플 데이터 만들어줌
        String input = IntStream.rangeClosed(1,10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num,num))
                .collect(Collectors.joining("\n"));//문자열 여러개 나왔을때 합쳐줌

        input += "목록?page=2\n";

        String out = AppTestRunner.run(input);


        assertThat(out)
                .doesNotContain("10 / 작가 10 / 명언 10")
                .doesNotContain("9 / 작가 9 / 명언 9")
                .doesNotContain("8 / 작가 8 / 명언 8")
                .doesNotContain("7 / 작가 7 / 명언 7")
                .doesNotContain("6 / 작가 6 / 명언 6")
                .contains("5 / 작가 5 / 명언 5")
                .contains("4 / 작가 4 / 명언 4")
                .contains("3 / 작가 3 / 명언 3")
                .contains("2 / 작가 2 / 명언 2")
                .contains("1 / 작가 1 / 명언 1");


    }

    @Test
    @DisplayName("목록?page=2 페이지 메뉴 출력")
    void t14(){

        //샘플 데이터 만들어줌
        String input = IntStream.rangeClosed(1,10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num,num))
                .collect(Collectors.joining("\n"));//문자열 여러개 나왔을때 합쳐줌

        input += "목록?page=2\n";

        String out = AppTestRunner.run(input);


        assertThat(out)
                .doesNotContain("10 / 작가 10 / 명언 10")
                .doesNotContain("9 / 작가 9 / 명언 9")
                .doesNotContain("8 / 작가 8 / 명언 8")
                .doesNotContain("7 / 작가 7 / 명언 7")
                .doesNotContain("6 / 작가 6 / 명언 6")
                .contains("5 / 작가 5 / 명언 5")
                .contains("4 / 작가 4 / 명언 4")
                .contains("3 / 작가 3 / 명언 3")
                .contains("2 / 작가 2 / 명언 2")
                .contains("1 / 작가 1 / 명언 1")
                .contains("페이지 : 1 / [2]");


    }

    @Test
    @DisplayName("목록?page=1, 페이지 메뉴 출력")
    void t15() {

        String input = IntStream.rangeClosed(1, 10)
                .mapToObj(num -> """
                        등록
                        명언 %d
                        작가 %d
                        """.formatted(num, num))
                .collect(Collectors.joining("\n"));

        input += "목록?page=1\n";

        String out = AppTestRunner.run(input);

        assertThat(out)
                .contains("10 / 작가 10 / 명언 10")
                .contains("9 / 작가 9 / 명언 9")
                .contains("8 / 작가 8 / 명언 8")
                .contains("7 / 작가 7 / 명언 7")
                .contains("6 / 작가 6 / 명언 6")
                .doesNotContain("5 / 작가 5 / 명언 5")
                .doesNotContain("4 / 작가 4 / 명언 4")
                .doesNotContain("3 / 작가 3 / 명언 3")
                .doesNotContain("2 / 작가 2 / 명언 2")
                .doesNotContain("1 / 작가 1 / 명언 1")
                .contains("페이지 : [1] / 2");


    }

}
