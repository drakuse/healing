package com.sji.member.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sji.member.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sql;

	// 회원가입 처리
	public int memberJoin(MemberVO memberVO) {
		return sql.insert("Member.memberJoin", memberVO);
	}

	// 아이디 중복확인
	public MemberVO idOverlap(String id) {
		return sql.selectOne("Member.idOverlap", id);
	}

	// 로그인 처리
	public MemberVO memberLogin(MemberVO memberVO) {
		return sql.selectOne("Member.memberLogin", memberVO);
	}

	// 관리자모드 회원목록 처리
	public List<MemberVO> memberList() {
		return sql.selectList("Member.memberList");
	}

	/*public MemberVO memberView(MemberVO memberVO) {
		return sql.selectOne("Member.memberView", memberVO);
	}*/
	
	public MemberVO memberView(String id) {
		return sql.selectOne("Member.memberView", id);
	}

	public void memberDelete(MemberVO memberVO) {
		sql.delete("Member.memberDelete", memberVO);		
	}

	public int memberModify(MemberVO memberVO) {
		return sql.update("Member.memberModify", memberVO);
	}

}
