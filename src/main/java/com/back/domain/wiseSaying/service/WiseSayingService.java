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

    public List<WiseSaying> findListDesc(String kw, String kwtype) {

        return switch (kwtype){
            case "content" -> wiseSayingRepository.findByContentContainingDesc(kw);
            case "author" ->wiseSayingRepository.findByAuthorContainingDesc(kw);
            default -> wiseSayingRepository.findByContentContainingOrAuthorContainingDesc(kw);
        };

    }


    public boolean delete(int id) {
        return wiseSayingRepository.delete(id);
    }

    public WiseSaying findByIdOrNull(int id) {
        return wiseSayingRepository.findByIdOrNull(id);
    }

    public void modify(WiseSaying wiseSaying, String newSaying, String newAuthor) {
        wiseSaying.setSaying(newSaying);
        wiseSaying.setAuthor(newAuthor);
        wiseSayingRepository.save(wiseSaying);
    }
}
