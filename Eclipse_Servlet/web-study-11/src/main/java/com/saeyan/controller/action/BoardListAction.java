package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

/**
 * 게시글 목록 조회 Action 클래스
 * - 사용자가 게시판 목록을 요청하면 실행됨
 * - Action 인터페이스를 구현하여 execute 메서드를 정의
 */
public class BoardListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String url = "/board/boardList.jsp"; // 게시판 목록 JSP 경로
		
		BoardDAO bDao = BoardDAO.getInstance(); // DAO 객체 가져오기 (싱글톤)
		
		List<BoardVO> boardList = bDao.selectAllBoards(); // DB에서 모든 게시글 리스트 가져오기
		
		request.setAttribute("boardList", boardList); // request 객체에 게시글 리스트 저장
		
		// 요청을 JSP로 전달 (forward)
		RequestDispatcher dispatcher = request.getRequestDispatcher(url); 
		dispatcher.forward(request, response);
	}
	
}
