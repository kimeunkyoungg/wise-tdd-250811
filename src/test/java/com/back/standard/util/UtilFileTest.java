package com.back.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

//9단계
//파일 생성 삭제 ''' 테스트 케이스
public class UtilFileTest {

    @Test
    @DisplayName("파일 생성")
    void t1(){

        //무언가를 세팅하고 -> 파일명
        String filePath = "text.txt";


        //수행하면 -> 파일을 만들기
        Util.file.touch(filePath);

        //결과가 나온다 -> 실제 파일이 존재하는가?
        boolean rst = Util.file.exists(filePath);


        //테스트 끝나면 txt파일 삭제


        assertThat(rst).isTrue();
    }

    @Test
    @DisplayName("파일 삭제")
    void t2(){

        //given
        String filePath = "test.txt";
        Util.file.touch(filePath);

        //when
        Util.file.delete(filePath);

        //then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isTrue();
    }

    @Test
    @DisplayName("파일 읽기/쓰기")
    void t3() {

        // given
        String filePath = "temp/test.txt";
        Util.file.set(filePath, "hello world"); // 파일 쓰기

        // when
        String content = Util.file.get(filePath, "");

        // then
        assertThat(content).isEqualTo("hello world");

        // 테스트가 끝나면 파일 삭제
        Util.file.delete(filePath);
    }

    @Test
    @DisplayName("파일 생성 - 경로에 폴더가 없는 경우")
    void t4() {

        // given
        String filePath = "temp/temp/test.txt";

        // when
        Util.file.touch(filePath); // 파일 생성

        // then
        boolean rst = Util.file.exists(filePath);
        assertThat(rst).isTrue();

        // 테스트가 끝나면 파일 삭제
        Util.file.delete(filePath);
    }
}
