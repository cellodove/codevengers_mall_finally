package ven.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;

public class BoardDeleteService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand( );
		BoardDAO boardDAO = new BoardDAO();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("qbrd_num"));
		
		usercheck = boardDAO.isBoardWriter(num, request.getParameter("qbrd_passwd"));
		int qbrd_num=Integer.parseInt(request.getParameter("qbrd_num"));
		if (usercheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardList.bf';");
			out.println("</script>");
			out.close();
			return null;
		}else {
			
			boardDAO.boardDelete(qbrd_num);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다.');");
			out.println("location.href='./BoardList.bf';");
			out.println("</script>");
			out.close();
		}
		result = boardDAO.boardDelete(qbrd_num);
		if (result == false) {
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./BoardList.bf");
		return null;
	}

}