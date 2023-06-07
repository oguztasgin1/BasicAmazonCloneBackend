package com.oguztasgin.controller;

import static com.oguztasgin.constants.ApiUrls.*;

import com.oguztasgin.dto.request.NewCreateUserRequestDto;
import com.oguztasgin.dto.request.UpdateRequestDto;
import com.oguztasgin.dto.response.UserFindAllResponseDto;
import com.oguztasgin.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {
    private final UserProfileService userProfileService;
    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.update(dto));
    }

    @GetMapping(FINDALL)
    public  ResponseEntity<List<UserFindAllResponseDto>> findAll(){
        return  ResponseEntity.ok(userProfileService.findAllUser());
    }
}
