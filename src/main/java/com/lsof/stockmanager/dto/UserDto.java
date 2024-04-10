package com.lsof.stockmanager.dto;

import com.lsof.stockmanager.model.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @Past
    private LocalDateTime birthday;

    /**
     * Converts an user object to a userDto
     * @param user object
     * @return userDto
     */
    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
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
    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
