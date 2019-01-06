package com.sji.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.sji.member.dao.MemberDAO;
import com.sji.member.vo.MemberVO;

@Service
public class MemberSerivce {

	@Autowired
	private MemberDAO memberDAO;

	private ModelAndView mav;

	private MemberVO memberVO;

	@Autowired
	private HttpSession session;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	// 회원가입처리
	public ModelAndView memberJoin(MemberVO memberVO) {
		/*
		 * 1. DAO 클래스의 회원가입 메소드 호출 2. 호출 결과를 가지고 회원가입 성공/실패 여부 판단해서 처리 2.1 성공하면
		 * loginForm.jsp 페이지로 이동 2.2 실패하면 joinForm.jsp를 다시 띄움.
		 */
		mav = new ModelAndView();
		int result = memberDAO.memberJoin(memberVO);
		if (result == 0) {
			// 회원가입 실패
			mav.setViewName("joinForm");
		} else {
			// 회원가입 성공
			mav.setViewName("loginForm");
		}
		return mav;
	}

	// 아이디 중복확인
	public void idOverlap(String id, HttpServletResponse response) throws IOException {
		memberVO = new MemberVO();
		memberVO = memberDAO.idOverlap(id);
		if (memberVO == null) {
			// 해당 아이디 사용 가능
			response.getWriter().print("1");
		} else {
			// 해당 아이디 사용 불가
			response.getWriter().print("0");
		}
	}

//	// 로그인 처리
//	public ModelAndView memberLogin(MemberVO memberVO, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		mav = new ModelAndView();
//		MemberVO loginMember = memberDAO.memberLogin(memberVO);
//		PrintWriter out = response.getWriter();
//
//		if (loginMember == null) {
//			System.out.println("로그인 실패");
//			out.println("<script>");
//			out.println("alert('아이디 또는 비밀번호가 틀립니다.');");
//			out.println("history.go(-1);</script>");
//			out.close();
//		} else {
//			System.out.println("로그인 성공");
//			session.setAttribute("loginId", loginMember.getId()); // 뷰에서 사용되는 파라미터이름
//			mav.addObject("loginMember", loginMember);
//			mav.setViewName("main");
//		}
//		return mav;
//	}

	// 로그인 처리(spring-security 적용)
	public ModelAndView memberLogin(MemberVO memberVO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		mav = new ModelAndView();
		MemberVO loginMember = memberDAO.memberLogin(memberVO);
		PrintWriter out = response.getWriter();

		if (passEncoder.matches(memberVO.getPassword(), loginMember.getPassword())) {
			System.out.println("로그인 성공");
			session.setAttribute("loginId", loginMember.getId()); // 뷰에서 사용되는 파라미터이름
			mav.addObject("loginMember", loginMember);
			mav.setViewName("main");
		} else {
			System.out.println("로그인 실패");
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 틀립니다.');");
			out.println("history.go(-1);</script>");
			out.close();
		}
		return mav;
	}

	// 관리자모드 회원리스트 처리
	public ModelAndView memberList() {
		mav = new ModelAndView();
		List<MemberVO> memberList = memberDAO.memberList();
		mav.addObject("memberList", memberList);
		mav.setViewName("memberList");
		return mav;
	}

	/*
	 * public ModelAndView memberView(MemberVO memberVO) { mav = new ModelAndView();
	 * MemberVO memberView = new MemberVO(); memberView =
	 * memberDAO.memberView(memberVO); mav.addObject("memberView", memberView);
	 * mav.setViewName("memberView"); return mav; }
	 */

	public ModelAndView memberView(String id) {
		mav = new ModelAndView();
		MemberVO memberView = new MemberVO();
		memberView = memberDAO.memberView(id);
		mav.addObject("memberView", memberView);
		mav.setViewName("memberView");
		return mav;
	}

	public ModelAndView memberDelete(MemberVO memberVO) {
		memberDAO.memberDelete(memberVO);
		mav.setViewName("main");
		return mav;
	}

	public ModelAndView memberModify(MemberVO memberVO, String newPassword) {
		mav = new ModelAndView();
		MemberVO loginMember = memberDAO.memberLogin(memberVO);

		if (passEncoder.matches(memberVO.getPassword(), loginMember.getPassword())) {
			System.out.println("비밀번호 체크완료");
			System.out.println(memberVO.toString());
			if (newPassword != null) {
				String encPassword = passEncoder.encode(newPassword);
				System.out.println("암호화 비번 확인 : " + encPassword);
				memberVO.setPassword(encPassword);
			} else {
				memberVO.setPassword(loginMember.getPassword());
			}
			int result = memberDAO.memberModify(memberVO);
			if (result == 0) {
				// 정보수정 성공
				mav.setViewName("main");
			} else {
				// 정보수정 실패
				mav.setViewName("main");
			}
		} else {
			System.out.println("비밀번호 틀림");
			mav.setViewName("main");
		}
		return mav;
	}
}
