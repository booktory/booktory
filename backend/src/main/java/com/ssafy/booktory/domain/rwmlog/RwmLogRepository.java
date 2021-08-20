package com.ssafy.booktory.domain.rwmlog;

import com.ssafy.booktory.domain.rwm.Rwm;
import com.ssafy.booktory.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RwmLogRepository extends JpaRepository<RwmLog, Long> {

    List<RwmLog> findAllByRwm(Rwm rwm);
    int countByRwm(Rwm rwm);
    Optional<RwmLog> findByUserAndRwm(User user, Rwm rwm);
    int countByUserAndRwm(User user, Rwm rwm);

}
