package com.ssafy.booktory.service;


import com.ssafy.booktory.domain.rwm.Rwm;
import com.ssafy.booktory.domain.rwm.RwmListResponseDto;
import com.ssafy.booktory.domain.rwm.RwmRepository;
import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.ssafy.booktory.domain.rwmlog.RwmLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RwmService {
    private final RwmRepository rwmRepository;
    private final RwmLogRepository rwmLogRepository;

    @Transactional
    public List<RwmListResponseDto> getRwmList() {
        List<RwmListResponseDto> rwmList = new ArrayList<>();
        List<Rwm> rwms = rwmRepository.findAll();
        for(Rwm rwm : rwms){
            rwmList.add(new RwmListResponseDto(rwm, rwmLogRepository.countByRwmAndModifiedDateIsNull(rwm)));
        }
        return rwmList;
    }
}
