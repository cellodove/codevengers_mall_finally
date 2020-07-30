package ven.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.dao.MemberDAO;
import ven.shop.model.MemberVO;

public class MemberDeleteService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("MemberDeleteService연결");
		
		String mem_id = request.getParameter("mem_id");
		String mem_passwd = request.getParameter("mem_passwd");
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMem_id(mem_id);
		memberVO.setMem_passwd(mem_passwd);
		
		// 내용을 출력할 수 있는 출력 스트림을 생성한다.
		PrintWriter out = response.getWriter( );
		
		memberDAO.memberDelete(memberVO);
		ActionCommand actionCommand = new ActionCommand();
		
		actionCommand.setRedirect(false);
		
		boolean deleteCheck = memberDAO.memberDelete(memberVO);
		if (deleteCheck==true) {
			out.println("<script type='text/javascript'>");
			out.println("alert('회원삭제되었습니다.')");
			out.println("</script>");
			System.out.println("회원삭제되었습니다.");
			
			HttpSession session = request.getSession( );
			session.setAttribute("mem_id", mem_id);
			actionCommand.setPath("./index.jsp");
			
		}else {
			out.println("<script type='text/javascript'>");
			out.println("alert('비밀번호가 일치하지 않습니다.')");
			out.println("</script>");
			System.out.println("비밀번호가 틀렸습니다.");
			actionCommand.setPath("./member/member_delete.jsp");
		}
		
		return actionCommand;

	}

}
