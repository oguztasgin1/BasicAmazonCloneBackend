package com.oguztasgin.controller;

import com.oguztasgin.dto.request.DoLoginRequestDto;
import com.oguztasgin.dto.request.RegisterRequestDto;
import com.oguztasgin.dto.request.RoleUpdateRequestDto;
import com.oguztasgin.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.oguztasgin.dto.response.DoLoginResponseDto;
import com.oguztasgin.exception.AuthException;
import com.oguztasgin.exception.EErrorType;
import com.oguztasgin.repository.entity.Auth;
import com.oguztasgin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.oguztasgin.constant.ApiUrls.*;

@RestController
@RequestMapping(VERSION+AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authservice;

    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        authservice.register(dto);
        return ResponseEntity.ok(true);
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<Auth> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
       return ResponseEntity.ok(authservice.doLogin(dto));
    }

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByUsernameOrEmail(@RequestHeader(value = "Authorization")String token,
                                                           @RequestBody UpdateByEmailOrUserNameRequestDto dto){
        return ResponseEntity.ok(authservice.updateByUsernameOrEmail(dto));

    }

    @GetMapping(FINDALL)
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<Auth>> findAll(){
        return  ResponseEntity.ok(authservice.findAll());
    }

    @PostMapping("/updateRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> updateRole(@RequestBody RoleUpdateRequestDto dto){
        return ResponseEntity.ok(authservice.updateRole(dto));
    }

}
