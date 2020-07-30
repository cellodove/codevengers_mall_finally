package ven.shop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.member.service.MemberAddService;
import ven.member.service.MemberCertifiedService;
import ven.member.service.MemberDeleteService;
import ven.member.service.MemberEmailCodeService;
import ven.member.service.MemberInfoService;
import ven.member.service.MemberLoginService;
import ven.member.service.MemberMainService;
import ven.member.service.MemberModifyChangeService;
import ven.member.service.MemberModifyService;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;

//@WebServlet("/MemberFrontController")
public class MemberFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서블릿 맵핑명을 설정한다.
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());

		// 포워딩 정보 저장
		ActionCommand actionCommand = null;
		// 메소드 규격화
		Action action = null;

		// 맵핑명 지정하고 서블릿 클래스 설정

		if (pathURL.equals("/MemberLogin.do")) {
			actionCommand = new ActionCommand();
			System.out.println("memberlogin.do연결");
			// 포워드로 한다.
			actionCommand.setRedirect(false);
			// 회원가입 페이지로 이동한다.
			actionCommand.setPath("./member/member_login.jsp");
			
		} else if (pathURL.equals("/MemberLoginCheck.do")) {
			action = new MemberLoginService();
			System.out.println("memberloginCheck.do연결");
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberAdd.do")) {
			action = new MemberAddService();
			System.out.println("memberAdd.do연결");
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberWrite.do")) {
			actionCommand = new ActionCommand();
			System.out.println("memberWrite.do연결");
			// 포워드로 한다.
			actionCommand.setRedirect(false);
			// 회원가입 페이지로 이동한다.
			actionCommand.setPath("./member/member_signup.jsp");

		} else if (pathURL.equals("/MemberMail.do")) {
			System.out.println("memberMail.do연결");
			action = new MemberCertifiedService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberMailCheck.do")) {
			System.out.println("memberMailCheck.do연결");
			action = new MemberEmailCodeService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		} else if (pathURL.equals("/MemberMain.do")) {
			action = new MemberMainService();
			System.out.println("memberMain.do연결");

			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberInfo.do")) {
			action = new MemberInfoService();
			System.out.println("memberInfo.do연결");

			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/MemberModify.do")) {
			action = new MemberModifyService();
			System.out.println("memberModify.do연결");

			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(pathURL.equals("/MemberDelete.do")) {
			actionCommand = new ActionCommand();
			System.out.println("memberDelete.do연결");
			String mem_id = request.getParameter("mem_id");
			request.setAttribute("mem_id", mem_id);
			// 포워드로 한다.
			actionCommand.setRedirect(false);
			// 회원가입 페이지로 이동한다.
			actionCommand.setPath("./member/member_delete.jsp");
			
		} else if(pathURL.equals("/MemberDeleteReal.do")) {
			System.out.println("MemberDeleteReal.do연결");
			action = new MemberDeleteService();

			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/MemberChangeModify.do")) {
			action = new MemberModifyChangeService();
			System.out.println("MemberChangeModify.do연결");
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		if (actionCommand != null) {
			// isRedirect 메소드 값이 false이면 forward 방식이고 true이면 redirect 방식이 된다.
			if (actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}


	// HTTP 요청이 get 메소드 방식인지를 확인하고 service 메소드를 호출한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	// HTTP 요청이 post 메소드 방식인지를 확인하고 service 메소드를 호출한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

}
