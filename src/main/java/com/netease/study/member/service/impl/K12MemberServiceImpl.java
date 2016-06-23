package com.netease.study.member.service.impl;

import org.springframework.stereotype.Service;

import com.netease.study.member.service.K12MemberService;

@Service
public class K12MemberServiceImpl implements K12MemberService{

    @Override
    public boolean login(String userName, String pass) {
        if ("admin".equals(userName) && "123".equals(pass)) {
            return true;
        }
        return false;
    }
}
