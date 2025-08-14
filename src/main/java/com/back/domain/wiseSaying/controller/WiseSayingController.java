package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.PageDto;
import com.back.Rq;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WiseSayingController {

    private Scanner sc;
    private WiseSayingService wiseSayingService;


    public WiseSayingController() {
        this.sc = AppContext.sc;
        this.wiseSayingService = AppContext.wiseSayingService;
    }

    public void actionAdd(){
        System.out.print("명언 : ");
        String saying = sc.nextLine();
        System.out.println("작가 : ");
        String author = sc.nextLine();


        WiseSaying wiseSaying = wiseSayingService.write(saying, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList(Rq rq){


        String kw = rq.getParam("keyword", "");
        String kwType = rq.getParam("keywordType", "");

        int pageSize = rq.getParamAsInt("pageSize", 5); //페이징 개수
        int page = rq.getParamAsInt("page", 1); //현재 페이지

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        PageDto pageDto = wiseSayingService.findListDesc(kw, kwType, pageSize, page); //원래는 목록 다 보여주도록

        pageDto.getContent()
                .stream()
                .forEach(wiseSaying -> System.out.printf("%d / %s / %s%n", wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getSaying()));

        // 1 / [2] 형식의 페이지 메뉴 생성
        System.out.println("----------------------");

        int totalPageCnt = pageDto.getTotalPageCnt();
        int currentPageNo = pageDto.getPage(); //현재 보고 있는 페이지 []로 나와야함

        String pageMenu = IntStream.rangeClosed(1, totalPageCnt)
                        .mapToObj(i -> i == currentPageNo ? "[%d]". formatted(i) : String.valueOf(i))
                                .collect(Collectors.joining(" / "));
        System.out.println("페이지 : " + pageMenu);
    }

    public void actionDelete(Rq rq) {

        int id = rq.getParamAsInt("id", -1);
        boolean deleted = wiseSayingService.delete(id);

        if(!deleted) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
    }

    public void actionModify(Rq rq) {

        int id = rq.getParamAsInt("id", -1);

        Optional<WiseSaying> opWiseSaying = wiseSayingService.findById(id);

        if(opWiseSaying.isEmpty()) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(opWiseSaying.get().getSaying()));
        String newSaying = sc.nextLine();
        System.out.println("작가(기존) : %s".formatted(opWiseSaying.get().getAuthor()));
        String newAuthor = sc.nextLine();

        wiseSayingService.modify(opWiseSaying.get(), newSaying, newAuthor);

    }
}
