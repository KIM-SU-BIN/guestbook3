package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.MainDao;
import com.javaex.vo.MainVo;

@Service
public class MainService {
	
	//필드
	@Autowired
	private MainDao mainDao;
	
	//생성자
	
	//메소드
	
	//메소드 일반
	
	//List 출력
	public List<MainVo> getMainList() {
		
		List<MainVo> mainList = mainDao.getMainList ();
		
		return mainList;
	}
	
	//글쓰기 write
	public int guestInsert(MainVo mainVo) {
		
		int count = mainDao.guestInsert(mainVo);
		
		return count;
	}
	

	
	//삭제 delete
	public int guestDelete (int no, String password) {
		
		int count = mainDao.guestDelete(no, password);

		return count;
	}
	
	
	
	public MainVo getMain(int no) {
		
		return mainDao.getMainList(no);
	}
}

	
	
	

