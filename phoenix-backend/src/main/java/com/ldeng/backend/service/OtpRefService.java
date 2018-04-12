package com.ldeng.backend.service;

import com.ldeng.backend.model.OtpRef;

public interface OtpRefService {
    OtpRef save(OtpRef otpRef);

    OtpRef findById(Long otpId);
}
