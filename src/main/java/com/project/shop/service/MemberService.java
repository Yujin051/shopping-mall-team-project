package com.project.shop.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.shop.entity.Member;
import com.project.shop.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService implements UserDetailsService{
	private final MemberRepository memberRepository;

//    private final List<UserDetails> members;

	
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		
		return memberRepository.save(member);
	}

	private void validateDuplicateMember(Member member) {
		Member findUser = memberRepository.findByEmail(member.getEmail());
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 회원.");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
	
	public List<Member> memberList() {
		return memberRepository.findAll();
	}
	
	public void memberDelete(Long id) {
		memberRepository.deleteById(id);
	}
	
}
