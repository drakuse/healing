package com.sji.member.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.sji.member.service.MemberSerivce;
import com.sji.member.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberSerivce ms;

	private ModelAndView mav;

	@Autowired
	private HttpSession session; // 세션 생성

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	// 프로젝트 시작시 띄워줄 페이지
	// RequestMethod = default값은 GET 방식
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("home 메소드 호출");
		return "loginForm"; // loginForm.jsp 호출
	}

	// 회원가입 페이지 호출
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		System.out.println("joinForm 메소드 호출");
		return "joinForm"; // joinForm.jsp 호출
	}

//	// 회원가입 처리
//	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
//	public ModelAndView memberJoin(@ModelAttribute MemberVO memberVO) {
//		mav = new ModelAndView();
//		mav = ms.memberJoin(memberVO);
//		return mav;
//	}

	// 회원가입 처리(spring-security적용)
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberVO memberVO) {
		mav = new ModelAndView();

		String encPassword = passEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(encPassword);
		System.out.println("암호화 비번 확인 : " + memberVO.getPassword());

		mav = ms.memberJoin(memberVO);
		return mav;
	}

	// 아이디 중복확인
	@RequestMapping(value = "/idOverlap", method = RequestMethod.POST)
	public void idOverlap(HttpServletResponse response, @RequestParam("id") String id) throws IOException {
		// @RequestParam == Request.getParameter 기능
		ms.idOverlap(id, response);
	}

	// 로그인 처리
	@RequestMapping(value = "/memberLogin", method = RequestMethod.POST)
	public ModelAndView memberLogin(HttpServletResponse response, @ModelAttribute MemberVO memberVO)
			throws IOException {
		System.out.println("login 메소드 호출");
		mav = new ModelAndView();
		mav = ms.memberLogin(memberVO, response);
		return mav;
	}

	// 로그아웃 처리
	@RequestMapping(value = "/memberLogout", method = RequestMethod.GET)
	public String memberLogout() {
		System.out.println("logout 메소드 호출");
		session.invalidate();
		return "loginForm"; // loginForm.jsp 호출
	}

	// 관리자 모드 회원 리스트 처리
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public ModelAndView memberList(HttpSession session) throws IOException {
		System.out.println("list 메소드 호출");
		String loginId = (String) session.getAttribute("loginId");
		if (loginId == null || !loginId.equals("admin")) {
			// 로그인 상태 재확인
			mav.setViewName("loginForm");
		} else {
			mav = ms.memberList();
		}
		return mav;
	}
	// 관리자 모드 회원 상세정보
	/*@RequestMapping(value = "/memberView", method = RequestMethod.GET)
	public ModelAndView memberView(HttpServletResponse response, @ModelAttribute MemberVO memberVO)
			throws IOException {
		System.out.println("회원정보 메소드 호출");
		mav = new ModelAndView();
		mav = ms.memberView(memberVO);
		return mav;
	}*/
	// 관리자 모드 회원 상세정보
		@RequestMapping(value = "/memberView", method = RequestMethod.GET)
		public ModelAndView memberView(HttpServletResponse response, @RequestParam("id") String id)
				throws IOException {
			System.out.println("회원정보 메소드 호출");
			mav = new ModelAndView();
			mav = ms.memberView(id);
			return mav;
		}
	
	//관리자 모드 회원 정보 삭제
	@RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
	public ModelAndView memberDelete(HttpServletResponse response, @ModelAttribute MemberVO memberVO)
			throws IOException {
		System.out.println("회원삭제 메소드 호출");
		mav = new ModelAndView();
		mav = ms.memberDelete(memberVO);
		return mav;
	}
	
	//회원정보 수정    
	@RequestMapping(value = "/memberInfo", method = RequestMethod.GET)
	public ModelAndView memberInfo(HttpSession session)
			throws IOException {
		System.out.println("회원정보 메소드 호출");
		String id=(String)session.getAttribute("loginId");		
		mav = new ModelAndView();		
		mav = ms.memberView(id);
		mav.setViewName("memberInfo");
		return mav;
	}
	
	@RequestMapping(value = "/memberModify", method = RequestMethod.POST)
	public ModelAndView memberModify(@ModelAttribute MemberVO memberVO, @RequestParam("newPassword")String newPassword) {
		mav = new ModelAndView();
		mav = ms.memberModify(memberVO, newPassword);
		return mav;
	}
}
