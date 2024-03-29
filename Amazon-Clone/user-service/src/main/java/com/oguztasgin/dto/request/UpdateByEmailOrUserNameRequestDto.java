package com.oguztasgin.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateByEmailOrUserNameRequestDto {
    private Long id;
    private String username;
    private String email;
}
