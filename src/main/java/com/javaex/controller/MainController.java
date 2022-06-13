package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.MainDao;
import com.javaex.vo.MainVo;

@Controller
public class MainController {

	//필드
	
	//생성자
	
	//메소드
	
	//메소드 일반
	//Guestbook 삭제
	@RequestMapping(value="/delete/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute MainVo mainVo) {
		System.out.println("GuestbookController->delete()");
		
		//데이터 가져오기
		MainDao mainDao = new MainDao();
		mainDao.delete(mainVo);
		/*
		MainDao.guestDelete(no, pw);
		*/
		return"redirect:/list";
	}
	
	
	
	
	
	//Guestbook 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no, Model model) {
		System.out.println("GuestbookController->deleteForm()");
		
		model.addAttribute("no", no);
		
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	
	
	//글쓰기 write
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute MainVo mainVo){
		System.out.println("GuestbookController->write()");
		
		MainDao mainDao = new MainDao();
		mainDao.guestInsert(mainVo);
		
		return "redirect:/delete";
		
	}
	
	
	
	
	//List 출력
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String mainList(Model model) {
		
		System.out.println("Guestbook3Controller>mainList()");
		
		//데이터 가져오기
		MainDao mainDao = new MainDao();
		List<MainVo> mainList = mainDao.getMainList();
				
		model.addAttribute("mainList", mainList);
				
		return"/WEB-INF/views/addList.jsp";	
	}

}
