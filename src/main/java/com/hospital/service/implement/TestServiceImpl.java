package com.hospital.service.implement;

import com.hospital.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    public String test() {
        return "test";
    }
}
