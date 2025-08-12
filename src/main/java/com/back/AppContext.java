package com.back;

import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;
import com.back.system.SystemController;

import java.util.Scanner;

public class AppContext {
    public static Scanner sc = new Scanner(System.in);
    public static WiseSayingService wiseSayingService;
    public static WiseSayingController wiseSayingController;
    public static SystemController systemController;
    public static WiseSayingRepository wiseSayingRepository;

    public static void init(Scanner sc){

        AppContext.sc=sc;
        wiseSayingRepository =new WiseSayingRepository();
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController(sc);
        systemController = new SystemController();
    }

    public static void init(){
        init(new Scanner(System.in));
    }

}
