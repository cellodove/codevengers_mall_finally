package ven.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;

public class BoardSearchService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		BoardDAO boardDAO = new BoardDAO();  

		List<?> boardList = new ArrayList<Object>();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = boardDAO.getListCount();

		boardList = boardDAO.getBoardlist(page, limit);
		int maxpage = (int) ((double) listcount / limit + 0.95);

		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;

		int endpage = startpage + 10 - 1;

		if (endpage > maxpage) {
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("searchlistcount", listcount);
		request.setAttribute("searchBoardlist", boardList);
		ActionCommand actionCommand = new ActionCommand();

		actionCommand.setRedirect(false);
		System.out.println("BoardSearchListService.java(boardList) : "+boardList);
		actionCommand.setPath("./board/boardSearchList.jsp");
		return actionCommand;
	}
}