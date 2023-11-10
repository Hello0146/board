package com.example.backend.member.service;

import com.example.backend.jwt.JwtTokenUtil;
import com.example.backend.member.Exception.MemberException;
import com.example.backend.member.domain.Member;
import com.example.backend.member.dto.param.CreateMemberParam;
import com.example.backend.member.dto.request.JoinRequest;
import com.example.backend.member.dto.request.LoginRequest;
import com.example.backend.member.dto.response.JoinResponse;
import com.example.backend.member.dto.response.LoginResponse;
import com.example.backend.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;


    public MemberService(MemberRepository memberRepository, MemberRepository memberRepository1, PasswordEncoder encoder,
                         AuthenticationManager authenticationManager,
                         JwtTokenUtil jwtTokenUtil,
                         UserDetailsService userDetailsService) {

        this.memberRepository = memberRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public HttpStatus checkIdDuplicate(String id) {
        isExistUserId(id);
        return HttpStatus.OK;
    }

    @Transactional
    public JoinResponse join(JoinRequest req) {

        saveMember(req);
        authenticate(req.getId(), req.getPwd());

        return new JoinResponse(req.getId());
    }

    private void saveMember(JoinRequest req) {
        // 아이디 중복 확인
        isExistUserId(req.getId());

        // 패스워드 일치 확인
        checkPwd(req.getPwd(), req.getCheckPwd());

        // 회원 정보 생성
        String encodedPwd = encoder.encode(req.getPwd());
        CreateMemberParam param = new CreateMemberParam(req, encodedPwd);

        Member member = memberRepository.save(param.toEntity());
        if (member == null) {
            throw new MemberException("회원 등록을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public LoginResponse login(LoginRequest req) {
        authenticate(req.getId(), req.getPwd());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getId());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return new LoginResponse(token, req.getId());
    }

    private void authenticate(String id, String pwd) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, pwd));
        } catch (DisabledException e) {
            throw new MemberException("인증되지 않은 아이디입니다.", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private void isExistUserId(String id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            throw new MemberException("이미 사용중인 아이디입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    private void checkPwd(String pwd, String checkPwd) {
        if (!pwd.equals(checkPwd)) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
    }

}


