package com.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private Scanner sc;
    private int lastId;
    List<WiseSaying> wiseSayings = new ArrayList<>();


    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {



        System.out.println("== 명언 앱 ==");
        while (true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            switch (cmd){
                case "등록" ->{
                    System.out.print("명언 : ");
                    String saying = sc.nextLine();
                    System.out.println("작가 : ");
                    String author = sc.nextLine();


                    lastId ++;
                    WiseSaying wiseSaying = new WiseSaying(lastId, saying, author);
                    wiseSayings.add(wiseSaying);
                    System.out.println("%d번 명언이 등록되었습니다.".formatted(lastId));
                }
                //assertThat(out).contains("2 / 작자미상 / 현재를 사랑하라.");
                case "목록" -> {
                    System.out.println("번호 / 작가 / 명언");
                    System.out.println("----------------------");
                    wiseSayings
                            .reversed()
                            .stream()
                            .forEach(wiseSaying -> System.out.printf("%d / %s / %s%n",
                                    wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getSaying()));

                }
                case "종료" ->{
                    return;
                }
            }
        }
    }


}
