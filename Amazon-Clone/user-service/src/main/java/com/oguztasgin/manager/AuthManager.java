package com.oguztasgin.manager;

import com.oguztasgin.dto.request.UpdateByEmailOrUserNameRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.oguztasgin.constants.ApiUrls.UPDATEBYUSERNAMEOREMAIL;

@FeignClient(name = "auth-user", decode404 = true,url = "http://localhost:9090/api/v1/auth")
public interface AuthManager {
    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByUsernameOrEmail(@RequestHeader(value = "Authorization")String token,
                                                           @RequestBody UpdateByEmailOrUserNameRequestDto dto);


}
