package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.MainService;
import com.javaex.vo.MainVo;

@Controller
public class MainController {

	//필드
	@Autowired
	private MainService mainService;		//new MainDao x : 주입해줘
	//private MainDao mainDao = new MainDao();
	
	//생성자
	
	//메소드
	
	//메소드 일반
	//List 출력
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String mainList(Model model) {
		
		System.out.println("Guestbook3Controller>mainList()");
		
		//데이터 가져오기
		//MainDao mainDao = new MainDao();
		List<MainVo> mainList = mainService.getMainList();
				
		model.addAttribute("mainList", mainList);
				
		return"addList";	
	}
	
	
	//글쓰기 write
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute MainVo mainVo){
		
		System.out.println("GuestbookController->write()");
		
		//MainDao mainDao = new MainDao();
		mainService.guestInsert(mainVo);
		
		return "redirect:/addList";
		
	}
	
	
	//Guestbook 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no, Model model) {
		
		System.out.println("GuestbookController->deleteForm()");
		
		//데이터 가져오기
		//MainDao mainDao = new MainDao();
		MainVo mainVo = mainService.getMain(no);
		model.addAttribute("no",no);
		
		return "deleteForm";
	}

	
	
	//Guestbook 삭제
	@RequestMapping(value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String guestDelete(@ModelAttribute MainVo mainVo) {
		System.out.println("GuestbookController->delete()");
		
		//데이터 가져오기
		//MainDao mainDao = new MainDao();
		
		int no = mainVo.getNo();
		String password = mainVo.getPassword();
		int count = mainService.guestDelete(no, password);
		
		System.out.println(count);
		
		return"redirect:/addList";
	}
	
}
