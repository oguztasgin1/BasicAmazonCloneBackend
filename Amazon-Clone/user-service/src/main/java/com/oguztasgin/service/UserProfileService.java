package com.oguztasgin.service;

import com.oguztasgin.dto.request.NewCreateUserRequestDto;
import com.oguztasgin.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.oguztasgin.dto.request.UpdateRequestDto;
import com.oguztasgin.dto.response.UserFindAllResponseDto;
import com.oguztasgin.exception.EErrorType;
import com.oguztasgin.exception.UserProfileException;
import com.oguztasgin.manager.AuthManager;
import com.oguztasgin.mapper.IUserMapper;
import com.oguztasgin.repository.IUserProfileRepository;
import com.oguztasgin.repository.entity.UserProfile;
import com.oguztasgin.utility.JwtTokenManager;
import com.oguztasgin.utility.ServiceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService extends ServiceManager<UserProfile, Long> {
    private final IUserProfileRepository userProfileRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthManager authManager;

    public UserProfileService(IUserProfileRepository userProfileRepository,JwtTokenManager jwtTokenManager,
                              AuthManager authManager) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
    }

    @Transactional
    public Boolean createUser(NewCreateUserRequestDto dto) {
        UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(dto);
        try {
            save(userProfile);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new UserProfileException(EErrorType.USER_NOT_CREATED);
        }
    }

    public Boolean update(UpdateRequestDto dto) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserProfileException(EErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepository.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw new UserProfileException(EErrorType.USER_NOT_FOUND);
        }
        // If user change email or username we must change authMicroServiceDB
        if (!dto.getEmail().equals(userProfile.get().getEmail())||!dto.getUsername().equals(userProfile.get().getUsername())){
            userProfile.get().setUsername(dto.getUsername());
            userProfile.get().setEmail(dto.getEmail());
            authManager.updateByUsernameOrEmail("Bearer "+dto.getToken(), UpdateByEmailOrUserNameRequestDto.builder()
                    .email(userProfile.get().getEmail())
                    .username(userProfile.get().getUsername())
                    .id(authId.get())
                    .build());
        }
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAddress(dto.getAddress());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAvatar(dto.getAvatar());
        update(userProfile.get());
        return true;
    }

    public List<UserFindAllResponseDto> findAllUser() {
        return findAll().stream().map(x->IUserMapper.INSTANCE.toUserFindAllResponseDto(x)).collect(Collectors.toList());
    }
}
