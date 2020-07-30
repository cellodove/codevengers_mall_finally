package ven.member.service;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.dao.MemberDAO;
import ven.shop.model.MemberVO;

public class MemberLoginService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		
		System.out.println("memberloginservice연결");
		
		String mem_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMem_id(mem_id);
		memberVO.setMem_passwd(mem_passwd);
		
		// 내용을 출력할 수 있는 출력 스트림을 생성한다.

		
		
		ActionCommand actionCommand = new ActionCommand();
		
		actionCommand.setRedirect(false);
		
		boolean loginCheck = memberDAO.loginCheck(memberVO);
		if (loginCheck==true) {

			System.out.println("로그인 성공했습니다.");
			
			HttpSession session = request.getSession( );
			session.setAttribute("mem_id", mem_id);
			actionCommand.setPath("./index.jsp");
			
		}else {

			actionCommand.setPath("./index.jsp");
		}
		
		return actionCommand;
	}

}
