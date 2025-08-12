package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

//비즈니스 로직
public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = AppContext.wiseSayingRepository;
    }

    public WiseSaying write(String saying, String author){

        WiseSaying wiseSaying = new WiseSaying(saying, author);
        wiseSayingRepository.save(wiseSaying);

        return  wiseSaying;

    }

    public List<WiseSaying> findListDesc() {
        return wiseSayingRepository.findListDesc();
    }
}
