package com.harun.macit.service.impl;

import com.harun.macit.advice.UserNotFound;
import com.harun.macit.dto.UserDto;
import com.harun.macit.entity.User;
import com.harun.macit.repository.UserRepository;
import com.harun.macit.service.UserService;
import com.harun.macit.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    //Add
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Admin");
        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    //Select *
    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos=users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return dtos;
    }

    //Select id
    @Override
    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return modelMapper.map(user.get(),UserDto.class);
        }
        throw new RuntimeException("Kullanıcı Bulunamadı");
    }


    //Update
    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> resultUser = userRepository.findById(id);
        if (resultUser.isPresent()){
            resultUser.get().setFirstName(user.getFirstName());
            resultUser.get().setLastName(user.getLastName());
            resultUser.get().setUpdatedAt(new Date());
            resultUser.get().setUpdateBy("Admin");
            return modelMapper.map(userRepository.save(resultUser.get()),UserDto.class);
        }
        return null;
    }

    //Delete
    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        UserDto[] dtos = modelMapper.map(data.getContent(),UserDto[].class);
        return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }

    //@Override
    //public Page<User> pagination(int currentPage, int pageSize) {
        //Pageable pageable= PageRequest.of(currentPage, pageSize);
        //return userRepository.findAll(pageable);
   // }
}
