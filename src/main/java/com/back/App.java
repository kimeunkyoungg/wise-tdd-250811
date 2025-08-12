package com.back;

import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.system.SystemController;

import java.util.Scanner;

public class App {

    private Scanner sc;
    private WiseSayingController wiseSayingController;
    private SystemController systemController;



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

            Rq rq = new Rq(cmd);
            String action = rq.getActionName();

            switch (action){
                case "등록" -> wiseSayingController.actionAdd();
                //assertThat(out).contains("2 / 작자미상 / 현재를 사랑하라.");
                case "목록" ->  wiseSayingController.actionList();

                case "삭제" -> wiseSayingController.actionDelete(rq);

                case "종료" ->{
                    systemController.actionExit();
                    return;
                }
            }
        }
    }
}
