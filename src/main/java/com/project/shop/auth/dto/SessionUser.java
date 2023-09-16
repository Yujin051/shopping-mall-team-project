package com.project.shop.auth.dto;

import com.project.shop.entity.Member;
import lombok.Getter;

@Getter
public class SessionUser {
    private String email;
    private String phonenum;

    public SessionUser(Member member) {
        this.email = member.getEmail();
        this.phonenum = member.getPhonenum();
    }
}
