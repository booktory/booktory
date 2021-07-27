package com.ssafy.booktory.domain.userclub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserClubTest {
    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
//
    }
}

