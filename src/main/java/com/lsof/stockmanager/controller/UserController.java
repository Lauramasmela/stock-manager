package com.lsof.stockmanager.controller;

import com.lsof.stockmanager.dto.UserDto;
import com.lsof.stockmanager.service.UserServiceContract;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceContract userService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PatchMapping("/validate/{user-id}")
    public ResponseEntity<Integer> validateAccount(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(userService.validateAccount(userId));
    }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> inValidateAccount(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(userService.invalidateAccount(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") Integer userId){
        userService.delete(userId);
        return ResponseEntity.accepted().build();
    }
}
