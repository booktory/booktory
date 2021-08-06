package com.ssafy.booktory.domain.rwmlog;

import com.ssafy.booktory.domain.rwm.Rwm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RwmLogRepository extends JpaRepository<RwmLog, Long> {

    @Query("select rl from RwmLog rl where rl.rwm = :rwm and rl.createdDate = rl.modifiedDate")
    List<RwmLog> findAllByRwmAndDateTime(@Param("rwm") Rwm rwm);
    @Query("select count(rl) from RwmLog rl where rl.rwm = :rwm and rl.createdDate = rl.modifiedDate")
    int countByRwmAndDateTime(Rwm rwm);
}
