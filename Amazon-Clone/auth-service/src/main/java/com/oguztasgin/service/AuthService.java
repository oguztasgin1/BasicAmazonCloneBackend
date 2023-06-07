package com.oguztasgin.service;

import com.oguztasgin.dto.request.*;
import com.oguztasgin.exception.AuthException;
import com.oguztasgin.exception.EErrorType;
import com.oguztasgin.manager.IUserProfileManager;
import com.oguztasgin.mapper.IAuthMapper;
import com.oguztasgin.repository.IAuthRepository;
import com.oguztasgin.repository.entity.Auth;
import com.oguztasgin.repository.enums.ERole;
import com.oguztasgin.utility.JwtTokenManager;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final IUserProfileManager userProfileManager;
    private JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository,IUserProfileManager userProfileManager,JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userProfileManager = userProfileManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    public boolean  register(RegisterRequestDto dto) {
        if (authRepository.isUsername(dto.getUsername()))
            throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);
        //AuthMS' ye kayıt ettim.
        Auth auth = save(IAuthMapper.INSTANCE.fromRegisterDto(dto));
        //UserMS' e kayıt ediyorum.
        userProfileManager.createUser(NewCreateUserRequestDto.builder()
                        .authId(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());
        return true;
    }

    public Auth doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth =  authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthException(EErrorType.AUTH_LOGIN_ERROR);
        // Login olunca bir tane Token alıyoruz.
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthException(EErrorType.TOKEN_ERROR);
        return auth.get();
    }

    public Boolean updateByUsernameOrEmail(UpdateByEmailOrUserNameRequestDto dto) {
        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthException(EErrorType.USER_NOT_FOUND);
        }
        auth.get().setEmail(dto.getEmail());
        auth.get().setUsername(dto.getUsername());
        update(auth.get());
        return true;
    }

    public Boolean updateRole(RoleUpdateRequestDto dto) {
        ERole role = null;
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()) {
            throw new AuthException(EErrorType.USER_NOT_FOUND);
        }
        try {
            role = ERole.valueOf(dto.getRole());
        } catch (Exception e) {
            throw new AuthException(EErrorType.ROLE_NOT_FOUND);
        }
        auth.get().setRole(role);
        update(auth.get());
        return true;
    }
}
