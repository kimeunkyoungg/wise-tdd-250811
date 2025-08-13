package com.back.standard.util;

import com.back.AppTestRunner;
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


        assertThat(rst).isTrue();
    }
}
