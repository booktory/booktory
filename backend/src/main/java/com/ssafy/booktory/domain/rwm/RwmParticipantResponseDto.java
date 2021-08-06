package com.ssafy.booktory.domain.rwm;

import com.ssafy.booktory.domain.rwmlog.RwmLog;
import com.ssafy.booktory.domain.rwmlog.RwmParticipantListArgDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RwmParticipantResponseDto {
    String rwmName;
    List<RwmParticipantListArgDto> userList = new ArrayList<>();

    public RwmParticipantResponseDto(String rwmName, List<RwmLog> rwmLogs){
        this.rwmName = rwmName;
        for(RwmLog rwmLog : rwmLogs)
            (this.userList).add(new RwmParticipantListArgDto(rwmLog));
    }
}
