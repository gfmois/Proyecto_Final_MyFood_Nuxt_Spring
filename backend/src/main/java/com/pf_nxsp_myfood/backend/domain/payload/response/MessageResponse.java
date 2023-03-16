package com.pf_nxsp_myfood.backend.domain.payload.response;

import com.pf_nxsp_myfood.backend.domain.common.utils.BaseUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class MessageResponse extends BaseUtils {
    private String message;
    private String status;
}