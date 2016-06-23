package com.netease.study.member.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.netease.study.member.service.K12MemberService;

@Component
public class K12MemberLogic {
    @Resource
    private K12MemberService k12MemberService;
    public boolean login(String userName,String pass){
        return k12MemberService.login(userName, pass);
    }
    
    
}
