package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    private int lastId=0;
    List<WiseSaying> wiseSayings = new ArrayList<>();

    public WiseSaying save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()){
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }
        
        return wiseSaying;
    }




    public boolean delete(int id){
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }



    public WiseSaying findByIdOrNull(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //content 검색
    public List<WiseSaying> findByContentContainingDesc(String kw) {
        return wiseSayings.stream()
                .filter(w -> w.getSaying().contains(kw))
                .toList()
                .reversed();
        // return wiseSayings.reversed(); //필터링을 하고 목록 보여지도록 수정해야함
    }

    //author 검색
    public List<WiseSaying> findByAuthorContainingDesc(String kw) {
        return wiseSayings.stream()
                .filter(w -> w.getAuthor().contains(kw))
                .toList()
                .reversed();
    }

    public List<WiseSaying>  findByContentContainingOrAuthorContainingDesc(String kw) {
        return wiseSayings.stream()
                .filter(w -> w.getAuthor().contains(kw) || w.getSaying().contains(kw))
                .toList()
                .reversed();
    }
}
