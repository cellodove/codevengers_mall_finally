package ven.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.model.QnABoardVO;

public class BoardAddService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		QnABoardVO qnABoardVO = new QnABoardVO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./boardUpload";
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int fileSize = 10 * 1024 * 1024;
		boolean result = false;
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			qnABoardVO.setMem_id(multipartRequest.getParameter("mem_id"));
			qnABoardVO.setQbrd_passwd(multipartRequest.getParameter("qbrd_passwd"));
			qnABoardVO.setQbrd_title(multipartRequest.getParameter("qbrd_title"));
			qnABoardVO.setQbrd_content(multipartRequest.getParameter("qbrd_content"));
			qnABoardVO.setQbrd_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = boardDAO.boardinsert(qnABoardVO);
			if (result == false) {
				System.out.println("게시판 등록 실패^^");
				return null;
			}
			System.out.println("게시판 등록 완");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardList.bf");
			return actionCommand;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
