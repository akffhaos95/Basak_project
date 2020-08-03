package com.basak.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.basak.app.model.CategoryVO;
import com.basak.app.model.CompanyVO;
import com.basak.app.model.MemberVO;
import com.basak.app.model.PagingVO;
import com.basak.app.model.SnackVO;
import com.basak.app.service.IReviewService;
import com.basak.app.service.ISnackService;

@Controller
public class SnackController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	ISnackService snackService;
	@Autowired
	IReviewService reviewService;

	@RequestMapping(value={"/snack", "/snack/list"})
	public String getSnackID(HttpSession session, Model model
			,@RequestParam(value="category", required=false, defaultValue="0") int category
			,@RequestParam(value="company", required=false, defaultValue="0") int company
			,@RequestParam(value="search", required=false, defaultValue="") String search) {
		logger.info("snack list");
		
		//로그인 세션
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			logger.info("snack list session false");
		} else {
			logger.info("snack list session true");
			model.addAttribute("member", member);
		}
		logger.info(""+category+"  "+company);
		//리스트 검색 메뉴 (회사, 종류)
		List<CompanyVO> snackCom = snackService.getCompanyInfo();
		List<CategoryVO> snackCate = snackService.getCategoryInfo();
		model.addAttribute("snackCom", snackCom);
		model.addAttribute("snackCate", snackCate);
		
		//일반 리스트, 회사 검색 리스트, 종류 검색 리스트
		List<SnackVO> snackId = snackService.getSnackId(company, category, search);
		model.addAttribute("snackid", snackId);
		return "snack/list";
	}
	
	@RequestMapping(value="/snack/info/{snackId}")
	public String getSnackInfo(@PathVariable int snackId, PagingVO vo, HttpSession session, Model model
			,@RequestParam(value="nowPage", required=false) String nowPage) {
		logger.info("snack info:"+snackId);
		
		// 로그인 세션
		MemberVO member = (MemberVO) session.getAttribute("member");
		if(member == null) {
			logger.info("snack info session false");
		} else {
			logger.info("snack info session true");
			model.addAttribute("member", member);
		}
		
		//삭제 시에 사용되는 snackId 적용
		SnackVO snack = snackService.getSnackInfo(snackId);
		model.addAttribute("snack", snack);
		
		//평균
		double avg = reviewService.getSnackAvg(snackId);
		model.addAttribute("avg", avg*20);
		
		//리뷰 페이징
		int total = reviewService.countReview(snackId);
		if(nowPage == null) nowPage = "1";
		vo = new PagingVO(total, Integer.parseInt(nowPage), 5);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", reviewService.selectReview(snackId, vo));		
		return "snack/info";
	}
	
	/* 미사용 : 관리자 권한에서 이루어 져야하는 동작 */
	@RequestMapping(value="/snack/insert", method=RequestMethod.POST)
	public String insertSnack(SnackVO snack, Model model) {
		logger.info("snack insert");
		snackService.insertSnack(snack);
		return "redirect:/snack";
	}
	
	/* 미사용 : 관리자 권한에서 이루어 져야하는 동작 */
	@RequestMapping(value="/snack/update", method=RequestMethod.POST)
	public String updateSnack(SnackVO snack, Model model) {
		logger.info("snack update");
		snackService.updateSnack(snack);
		return "redirect:/snack/"+snack.getSnackId();
	}
	
	/* 미사용 : 관리자 권한에서 이루어 져야하는 동작 */
	@RequestMapping(value="/snack/delete", method=RequestMethod.POST)
	public String deleteEmp(int snackID, Model model) {
		logger.info("snack delete post");
		snackService.deleteSnack(snackID);
		return "redirect:/snack";
	}
}