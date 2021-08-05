package com.ssafy.booktory.domain.rwmlog;

import com.ssafy.booktory.domain.rwm.Rwm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RwmLogRepository extends JpaRepository<RwmLog, Long> {
    int countByRwmAndModifiedDateIsNull(Rwm rwm);
}
