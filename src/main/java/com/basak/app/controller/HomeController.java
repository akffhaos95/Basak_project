package com.basak.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.basak.app.model.ChartMap;
import com.basak.app.model.MemberVO;
import com.basak.app.model.PagingVO;
import com.basak.app.service.IRecommendService;
import com.basak.app.service.IReviewService;

import net.sf.json.JSONArray;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	IRecommendService recommendService;
	@Autowired
	IReviewService reviewService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session, PagingVO vo, Model model
			,@RequestParam(value="nowPage", required=false) String nowPage) {
		logger.info("home");
		
		//로그인 세션
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			logger.info("session false");
		} else {
			logger.info("session true");
			model.addAttribute("member", member);
		}
		
		//추천 페이징
		int total = recommendService.countRecommend();
		if(nowPage == null) nowPage = "1";
		vo = new PagingVO(total, Integer.parseInt(nowPage), 5);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", recommendService.selectRecommend(vo));		
		return "home";
	}
	
	@RequestMapping(value = "/chart")
	public String chart(Model model, HttpSession session) {
		logger.info("chart");
		MemberVO member = (MemberVO) session.getAttribute("member");
		model.addAttribute("member", member);
		List<ChartMap> snackList = reviewService.getSnackChart();
		List<ChartMap> companyList = reviewService.getCompanyChart();
		List<ChartMap> categoryList = reviewService.getCategoryChart();
		model.addAttribute("snackJson", JSONArray.fromObject(snackList));
		model.addAttribute("companyJson", JSONArray.fromObject(companyList));
		model.addAttribute("categoryJson", JSONArray.fromObject(categoryList));
		return "chart";
	}	
	
	@RequestMapping(value = "/error/404")
	public String error() {
		return "error/404";
	}	
}
