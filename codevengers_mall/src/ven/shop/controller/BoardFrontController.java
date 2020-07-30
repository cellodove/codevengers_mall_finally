package ven.shop.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.board.service.BoardAddService;
import ven.board.service.BoardDeleteService;
import ven.board.service.BoardDetailService;
import ven.board.service.BoardListService;
import ven.board.service.BoardModifyDetailService;
import ven.board.service.BoardModifyService;
import ven.board.service.BoardReplyMoveService;
import ven.board.service.BoardReplyService;
import ven.board.service.BoardSearchListService;

import ven.shop.action.Action;
import ven.shop.command.ActionCommand;


//@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		ActionCommand actionCommand = null;
		Action action = null;

		if (pathURL.equals("/BoardList.bf")) {
			action = new BoardListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (pathURL.equals("/BoardWrite.bf")) {
			actionCommand = new ActionCommand();

			actionCommand.setRedirect(false);

			actionCommand.setPath("./board/board_write.jsp");
			
		} else if (pathURL.equals("/BoardAdd.bf")) {
			action = new BoardAddService();

			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (pathURL.equals("/BoardDetail.bf")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if (pathURL.equals("/BoardReply.bf")) {
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		
		}else if(pathURL.equals("/BoardReplyMove.bf")) {
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (pathURL.equals("/BoardModify.bf")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardModifyService.bf")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardDelete.bf")) {
			actionCommand = new ActionCommand();
			actionCommand.setRedirect(false);
			actionCommand.setPath("./board/board_delete.jsp");
		} else if (pathURL.equals("/BoardDeleteService.bf")) {

			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (pathURL.equals("/BoardSearchList.bf")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (actionCommand != null) {
			if (actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI();
		String serv[] = url.split("/"); 
		
		url = serv[serv.length - 1];
		
		System.out.println(url);
		
		// ;sessionid 값이 있을수 있으므로 한번더 반복작업.
		
		String serv2[] = url.split(";");
		
		url = serv2[0];
		
		
		String site = null;
		
		if(url.equals("review_write.credu")){
			// review_write.credu 요청
			site = "review_write";
			
		}
		else if(url.equals("review.credu")){
			// review.credu 요청
			site = "review";
			
		}
		else if(url.equals("review_read.credu")){
			// review_read.credu 요청
			site = "review_read";
			
			
		}
		else if(url.equals("review_delete.credu")){
		
			site = "review_delete";
			
		}
	
		RequestDispatcher dis = request.getRequestDispatcher(site);
		dis.forward(request, response);
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		service(request, response);
	}

}