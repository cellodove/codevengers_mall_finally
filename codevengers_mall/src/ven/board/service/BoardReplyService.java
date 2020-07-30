package ven.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.model.QnABoardVO;

public class BoardReplyService implements Action {

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		QnABoardVO qnABoardVO = new QnABoardVO();
		int result = 0;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		
		try {
			
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int num=Integer.parseInt(multipartRequest.getParameter("qbrd_num"));
			System.out.println("답글서비스"+ multipartRequest.getParameter("qbrd_title"));
			qnABoardVO.setQbrd_num(Integer.parseInt(multipartRequest.getParameter("qbrd_num")));
			qnABoardVO.setMem_id(multipartRequest.getParameter("mem_id"));
			qnABoardVO.setQbrd_passwd(multipartRequest.getParameter("qbrd_passwd"));
			qnABoardVO.setQbrd_title(multipartRequest.getParameter("qbrd_title"));
			qnABoardVO.setQbrd_content(multipartRequest.getParameter("qbrd_content"));
			qnABoardVO.setQbrd_category(multipartRequest.getParameter("qbrd_category"));
			qnABoardVO.setQbrd_answer(multipartRequest.getParameter("qbrd_answer"));
			qnABoardVO.setQbrd_secret(multipartRequest.getParameter("qbrd_secret"));
			qnABoardVO.setQbrd_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			result = boardDAO.boardReply(qnABoardVO);
			if (result == 0) {
				System.out.println("답변 실패");
				return null;
			}
			System.out.println("답변 성공");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.bf?qbrd_num=" + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionCommand;
	}

}
