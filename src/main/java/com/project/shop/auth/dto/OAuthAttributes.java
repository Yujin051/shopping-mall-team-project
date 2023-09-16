package com.project.shop.auth.dto;

import com.project.shop.constant.RoleType;
import com.project.shop.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {  //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스이다.
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String email;
    private String phonenum;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } else if("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String phonenum) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.email = email;
        this.phonenum = phonenum;
    }

    // 네이버 API 데이터 받아오기
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .phonenum((String) response.get("mobile"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }




    // 구글 API 데이터 받아오기
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()

                .email((String) attributes.get("email"))
                .phonenum((String) attributes.get("mobile"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .email((String) kakaoAccount.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .phonenum(phonenum)
                .role(RoleType.USER)
                .build();
    }
}
