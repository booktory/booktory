package com.ssafy.booktory.service;


import com.ssafy.booktory.domain.rwm.Rwm;
import com.ssafy.booktory.domain.rwm.RwmListResponseDto;
import com.ssafy.booktory.domain.rwm.RwmParticipantResponseDto;
import com.ssafy.booktory.domain.rwm.RwmRepository;
import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.ssafy.booktory.domain.rwmlog.RwmLogRepository;
import com.ssafy.booktory.domain.user.User;
import com.ssafy.booktory.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RwmService {
    private final RwmRepository rwmRepository;
    private final RwmLogRepository rwmLogRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<RwmListResponseDto> getRwmList() {
        List<RwmListResponseDto> rwmList = new ArrayList<>();
        List<Rwm> rwms = rwmRepository.findAll();
        for(Rwm rwm : rwms){
            rwmList.add(new RwmListResponseDto(rwm, rwmLogRepository.countByRwmAndDateTime(rwm)));
        }
        return rwmList;
    }

    @Transactional
    public RwmParticipantResponseDto getParticipant(Long id) {
        Rwm rwm = rwmRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 Read With Me방 입니다."));
        List<RwmLog> rwmLogs = rwmLogRepository.findAllByRwmAndDateTime(rwm);

        return new RwmParticipantResponseDto(rwm.getName(), rwmLogs);
    }

    public RwmLog enterTheRoom(Long userId, Long rwmId, String bookName) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NoSuchElementException("사용자 정보가 존재하지 않습니다."));
        Rwm rwm = rwmRepository.findById(rwmId)
                .orElseThrow(()-> new NoSuchElementException("존재하지 않는 rwm방 입니다."));
        RwmLog rwmLog = RwmLog.builder()
                .user(user)
                .rwm(rwm)
                .bookName(bookName)
                .build();
        return rwmLogRepository.save(rwmLog);
    }
//
//    public RwmLog exitTheRoom(Long userId, Long rwmId){
//
//    }
}
