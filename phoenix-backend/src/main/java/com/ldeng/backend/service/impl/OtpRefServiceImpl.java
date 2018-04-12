package com.ldeng.backend.service.impl;

import com.ldeng.backend.model.OtpRef;
import com.ldeng.backend.repository.OtpRefRepository;
import com.ldeng.backend.service.OtpRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpRefServiceImpl implements OtpRefService {
    @Autowired
    private OtpRefRepository otpRefRepository;

    @Override
    public OtpRef save(OtpRef otpRef) {
        return otpRefRepository.save(otpRef);
    }

    @Override
    public OtpRef findById(Long otpId) {
        return otpRefRepository.findById(otpId).get();
    }
}
