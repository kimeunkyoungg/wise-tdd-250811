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

    public List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();
    }
}
