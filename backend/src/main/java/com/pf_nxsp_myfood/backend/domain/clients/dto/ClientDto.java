package com.pf_nxsp_myfood.backend.domain.clients.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientDto extends BaseUtils {
    @NotNull
    @NotBlank
    private String id_client;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String phone;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    @NotNull
    @NotBlank
    private String avatar;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SingleClient<T> {
        private T client;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MultipleClients {
        private List<ClientDto> clients;
    }
}
