package ven.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.dao.MemberDAO;
import ven.shop.model.MemberVO;

public class MemberAddService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberAddService연결");
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		ActionCommand actionCommand = new ActionCommand();
		
		boolean result = false;
		try {
			String mem_email = request.getParameter("mem_email");
			
			memberVO.setMem_id(request.getParameter("mem_id"));
			memberVO.setMem_passwd(request.getParameter("mem_passwd"));
			memberVO.setMem_name(request.getParameter("mem_name"));
			memberVO.setMem_birth(java.sql.Date.valueOf(request.getParameter("mem_birth")));
			memberVO.setMem_tel1(Integer.parseInt(request.getParameter("mem_tel1")));
			memberVO.setMem_tel2(Integer.parseInt(request.getParameter("mem_tel2")));
			memberVO.setMem_tel3(Integer.parseInt(request.getParameter("mem_tel3")));
			memberVO.setMem_zipcode(Integer.parseInt(request.getParameter("mem_zipcode")));
			memberVO.setMem_address1(request.getParameter("mem_address1"));
			memberVO.setMem_address2(request.getParameter("mem_address2"));
			memberVO.setMem_gender(request.getParameter("mem_gender"));
			memberVO.setMem_email(request.getParameter("mem_email"));
			memberVO.setMem_email_ck(0);
			memberVO.setMem_grade("Bronze");
			memberVO.setMem_point(0);
			memberVO.setMem_receive_email(Integer.parseInt(request.getParameter("mem_receive_email")));
			memberVO.setMem_receive_sms(Integer.parseInt(request.getParameter("mem_receive_sms")));
			memberVO.setMem_adminmemo(null);
			memberVO.setMem_group(null);
			memberVO.setMem_manager(0);
			
			result = memberDAO.memberInsert(memberVO);
			
			if (result == false) {
				System.out.println("회원가입완료");
				return null;
			}
			System.out.println("회원가입완료");

			request.setAttribute("mem_email", mem_email);
			System.out.println(mem_email);
			
			actionCommand.setRedirect(false);
			actionCommand.setPath("./member/member_email_check.jsp");
			return actionCommand;

		} catch (Exception e) {
			System.out.println("memberAddService 에러");
			e.printStackTrace();
		}

		return null;
	}


}
