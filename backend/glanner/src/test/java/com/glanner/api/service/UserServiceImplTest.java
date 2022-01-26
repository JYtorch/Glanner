package com.glanner.api.service;

import com.glanner.api.dto.request.UserSaveReqDto;
import com.glanner.core.domain.user.DailyWorkSchedule;
import com.glanner.core.domain.user.Schedule;
import com.glanner.core.domain.user.User;
import com.glanner.core.domain.user.UserRoleStatus;
import com.glanner.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void init(){
        createUser();
    }

    @Test
    public void testJoin() throws Exception{
        //given
        UserSaveReqDto reqDto = UserSaveReqDto.builder()
                .email("cherish8513@naver.com")
                .name("싸피")
                .password("1234")
                .phoneNumber("010-1234-5678")
                .build();

        //when
        User user = reqDto.toEntity();
        user.changePassword(passwordEncoder.encode(user.getPassword()));

        validateDuplicateMember(user);

        Schedule schedule = Schedule.builder()
                .build();

        user.changeSchedule(schedule);
        User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser.getSchedule()).isEqualTo(schedule);
    }

    private void validateDuplicateMember(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public void createUser(){
        User user = User.builder()
                .phoneNumber("010-6575-2938")
                .email("cherish8513@naver.com")
                .name("JeongJooHeon")
                .interests("#난그게재밌더라강식당다시보기#")
                .password("1234")
                .role(UserRoleStatus.ROLE_USER)
                .build();

        Schedule schedule = Schedule.builder()
                .build();

        DailyWorkSchedule workSchedule = DailyWorkSchedule.builder()
                .content("hard")
                .title("work")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusHours(3))
                .build();
        schedule.addDailyWork(workSchedule);
        user.changeSchedule(schedule);
        userRepository.save(user);
        em.flush();
        em.clear();
    }

}
