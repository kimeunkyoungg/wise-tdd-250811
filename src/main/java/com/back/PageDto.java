package com.back;


//레포지토리에서 페이지에 관련된 정보를 가져오기 위한 클래스
//아이템 == 명언

import com.back.domain.wiseSaying.entity.WiseSaying;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor //롬복 생성자
public class PageDto {

    private int page; //현재 페이지
    private int pageSize; //페이지 당 아이템 수
    private int totalItems; //전체 아이템 수
    private List<WiseSaying> content;
}
