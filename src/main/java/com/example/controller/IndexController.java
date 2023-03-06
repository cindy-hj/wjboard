package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.BoardService;
import com.example.vo.Board;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
	@Autowired
	private BoardService s;
	
	@RequestMapping(value="/boardList", method=RequestMethod.GET)
	@ResponseBody
	public List<Board> boardList() {
		return s.getBoard();
	}
	
	@RequestMapping(value="/writeAction", method=RequestMethod.POST)
	@ResponseBody
	public String writeAction(HttpServletRequest request) {
		Board b = new Board();
		b.setTitle(request.getParameter("title"));
		b.setContents(request.getParameter("contents"));
		b.setImage(request.getParameter("image"));
		
		s.addBoard(b);
		
		return "write ok";
	}
	
	@RequestMapping(value="/writeAction1", method=RequestMethod.POST)
	@ResponseBody
	public String writeAction1(HttpServletRequest request, 
			@RequestParam("title")String title,
			@RequestParam("contents")String contents) {	
		Board b = new Board();
		b.setTitle(title);
		b.setContents(contents);
		b.setImage("nofile");
		
		s.addBoard(b);
		
		return "write1 ok";
	}
	
	@RequestMapping(value="/boardView", method=RequestMethod.GET)
	@ResponseBody
	public Board boardList(@RequestParam("idx")int idx) {
		return s.getBoardOne(idx);
	}
}
