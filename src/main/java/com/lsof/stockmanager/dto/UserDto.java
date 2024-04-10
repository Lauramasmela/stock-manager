package com.lsof.stockmanager.dto;

import com.lsof.stockmanager.model.Role;
import com.lsof.stockmanager.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    /**
     * Converts an user object to a userDto
     * @param user object
     * @return userDto
     */
    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    /**
     * Converts a userDTO to a user obejct
     * @param userDto u
     * @return user object
     */
    public static UserDto toEntity(UserDto userDto){
        return UserDto.builder()
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
