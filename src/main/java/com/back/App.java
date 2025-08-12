package com.back;

import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.system.SystemController;

import java.util.Scanner;

public class App {

    Scanner sc;
    WiseSayingController wiseSayingController;
    SystemController systemController;



    public App() {
        this.sc = AppContext.sc;
        wiseSayingController = AppContext.wiseSayingController;
        systemController =AppContext.systemController;
    }

    public void run() {

        System.out.println("== 명언 앱 ==");

        while (true){
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            switch (cmd){
                case "등록" -> wiseSayingController.actionAdd();
                //assertThat(out).contains("2 / 작자미상 / 현재를 사랑하라.");
                case "목록" ->  wiseSayingController.actionList();

                case "종료" ->{
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}
