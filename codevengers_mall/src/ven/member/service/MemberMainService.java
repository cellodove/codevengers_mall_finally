package ven.member.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.shop.action.Action;
import ven.shop.command.ActionCommand;

public class MemberMainService implements Action {
	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("memberMainservice연결");
		
		ActionCommand actionCommand = new ActionCommand();
		//리다이렉트안하니깐 포워드로하고
		actionCommand.setRedirect(false);
		//글목록 페이지로 이동한다.
		actionCommand.setPath("./member/main_form.jsp");
		//저 두개내용을 리턴해준다.
		return actionCommand;
	}

}
