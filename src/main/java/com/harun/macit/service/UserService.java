package com.harun.macit.service;

import java.util.List;

import com.harun.macit.dto.UserDto;
import com.harun.macit.entity.User;
import com.harun.macit.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    UserDto createUser (UserDto user);

    List<UserDto> getUsers();

    UserDto getUser(Long id);

    UserDto updateUser(Long id,UserDto user);

    Boolean deleteUser(Long id);

    Page<User> pagination(Pageable pageable);

    Slice<User> slice(Pageable pageable);

    CustomPage<UserDto> customPagination (Pageable pageable);
}
