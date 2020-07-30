package ven.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.model.QnABoardVO;

public class BoardModifyDetailService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		QnABoardVO qnABoardVO = new QnABoardVO();
		int num = Integer.parseInt(request.getParameter("qbrd_num"));
		qnABoardVO = boardDAO.getDetail(num);
		if (qnABoardVO == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		request.setAttribute("qnABoardVO", qnABoardVO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_modify.jsp");
		return actionCommand;
	}

}
