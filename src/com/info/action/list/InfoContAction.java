package com.info.action.list;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.info.action.Action;
import com.info.action.ActionForward;
import com.info.model.DTO;
import com.info.model.InfoDAO;
import com.info.model.InfoDTO;
import com.info.model.ReplyDTO;

public class InfoContAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int info_no = Integer.parseInt(request.getParameter("no"));
		String info_genre = request.getParameter("genre");
		String nowPage = request.getParameter("page");
		InfoDAO dao = InfoDAO.getInstance();
		dao.infoHit(info_no);
		DTO contdto = dao.getDto(info_no, info_genre);
		
		request.setAttribute("contDTO", contdto);
		request.setAttribute("genre", info_genre);
		request.setAttribute("page", nowPage);
		
		List<ReplyDTO> repList = dao.getrep(info_no);

		List<InfoDTO> list = paging(request, info_genre, nowPage);

		request.setAttribute("list", list);
		request.setAttribute("repList", repList);

		ActionForward next = new ActionForward();
		next.setRedirect(false);
		next.setPath("./info/info_cont.jsp");
		return next;
	}
}
