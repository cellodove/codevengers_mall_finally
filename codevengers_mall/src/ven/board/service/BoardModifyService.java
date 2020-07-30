package ven.board.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ven.shop.dao.BoardDAO;
import ven.shop.action.Action;
import ven.shop.command.ActionCommand;
import ven.shop.model.QnABoardVO;

public class BoardModifyService implements Action{

	@Override
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		QnABoardVO qnABoardVO = new QnABoardVO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multipartRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multipartRequest.getParameter("qbrd_num"));
			boolean usercheck = boardDAO.isBoardWriter(num, multipartRequest.getParameter("qbrd_passwd"));
			if (usercheck == false) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.')");
				out.println("location.href='./BoardList.bf';");
				out.println("</script>");
				out.close();
				return null;
			}
			qnABoardVO.setQbrd_num(num);
			qnABoardVO.setMem_id(multipartRequest.getParameter("mem_id"));
			qnABoardVO.setQbrd_title(multipartRequest.getParameter("qbrd_title"));
			qnABoardVO.setQbrd_content(multipartRequest.getParameter("qbrd_content"));
			qnABoardVO.setQbrd_file(multipartRequest.getFilesystemName((String)multipartRequest.getFileNames().nextElement()));
			qnABoardVO.setOld_file(multipartRequest.getParameter("old_file"));
			result = boardDAO.boardModify(qnABoardVO);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			actionCommand.setRedirect(true);
			actionCommand.setPath("./BoardDetail.bf?num=" +qnABoardVO.getQbrd_num());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}