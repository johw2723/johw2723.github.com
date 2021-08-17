package com.cal.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cal.domain.CalVO;
import com.cal.service.CalService;

@Controller
@RequestMapping("/cal/*")
public class CalController {

private static final Logger logger = LoggerFactory.getLogger(CalController.class);
 
	@Inject
	CalService service;
		 
	// 계산 입력 get
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void getInsert() throws Exception {
		logger.info("get insert");
	}
		
	// 계산 입력 post
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String postInsert(CalVO vo, Model model) throws Exception {
		logger.info("post insert");
		  
		service.insert(vo);
		
		model.addAttribute("result", vo.getResult());
		model.addAttribute("op1", vo.getOp1());
		model.addAttribute("op", vo.getOp());
		model.addAttribute("op2", vo.getOp2());
		
		return "cal/insert_result";
		
		//return "redirect:/";
	}
	
	// 계산 조회 get
		@RequestMapping(value = "/select", method = RequestMethod.GET)
		public void postSelect() throws Exception {
			logger.info("get select");
		}
	
	// 계산 조회 post
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public String postSelect(int calNum, CalVO vo, Model model) throws Exception {
		logger.info("post select");
		  
		vo = service.read(calNum);
		
		model.addAttribute("calNum", vo.getCalNum());
		model.addAttribute("result", vo.getResult());
		model.addAttribute("op1", vo.getOp1());
		model.addAttribute("op", vo.getOp());
		model.addAttribute("op2", vo.getOp2());
		
		return "cal/select_result";
		
		//return "redirect:/";
	}
	
	// 계산 수정 get
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void postUpdate() throws Exception {
		logger.info("get update");
	}
		
	// 계산 수정 post
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String postUpdate(CalVO vo, Model model) throws Exception {
		logger.info("post update");
		  
		service.update(vo);
		
		model.addAttribute("calNum", vo.getCalNum());
		model.addAttribute("result", vo.getResult());
		model.addAttribute("op1", vo.getOp1());
		model.addAttribute("op", vo.getOp());
		model.addAttribute("op2", vo.getOp2());
		
		return "cal/update_result";
			
		//return "redirect:/";
	}
	
	// 계산 삭제 get
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void postdelete() throws Exception {
		logger.info("get delete");
	}
			
	// 계산 삭제 post
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postdelete(int calNum, Model model) throws Exception {
		logger.info("post delete");
		  
		service.delete(calNum);
		
		model.addAttribute("calNum", calNum);
	
		return "cal/delete_result";
				
		//return "redirect:/";
	}
}
