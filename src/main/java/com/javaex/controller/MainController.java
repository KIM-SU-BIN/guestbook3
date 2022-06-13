package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.MainDao;
import com.javaex.vo.MainVo;

@Controller
public class MainController {

	//필드
	
	//생성자
	
	//메소드
	
	//메소드 일반
	//글쓰기
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute MainVo maVo){
		System.out.println("GuestbookController->write()");
		
		MainDao mainDao = new MainDao();
		mainDao.guestInsert(maVo);
		
		return "redirect:/list";
		
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
