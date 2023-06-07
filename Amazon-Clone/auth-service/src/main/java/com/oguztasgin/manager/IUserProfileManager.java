package com.oguztasgin.manager;

import com.oguztasgin.dto.request.NewCreateUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.oguztasgin.constant.ApiUrls.SAVE;
@Component
@FeignClient(
        name = "user-profile-service-feign",
        url = "http://localhost:9091/api/v1/user",
        decode404 = true
)
public interface IUserProfileManager {
    @PostMapping(SAVE)
    ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);
}
