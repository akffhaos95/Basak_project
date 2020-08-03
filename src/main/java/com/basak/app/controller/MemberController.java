package com.basak.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.basak.app.UserMailSendService;
import com.basak.app.model.MemberVO;
import com.basak.app.model.PagingVO;
import com.basak.app.service.IMemberService;
import com.basak.app.service.IReviewService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	IMemberService memberService;

	@Autowired
	IReviewService reviewService;
	
	@Autowired
	UserMailSendService mailsender;
	
	// 회원 목록
	@RequestMapping("list.do")
	public String memberList(Model model) {
		logger.info("member list.do");
		
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/list";
	}
	
	// 회원 등록
	@RequestMapping("write.do")
	public String memberWrite() {
		logger.info("member write.do");
		
		return "member/write";
	}
	
	// 회원 등록 체크(아이디 중복 체크, 메일 서비스, 인증키)
	@RequestMapping("writeCheck.do")
	public String writeCheck(@ModelAttribute MemberVO vo, Model model, HttpServletRequest request) {
		logger.info("member writeCheck.do");
		
		boolean check = memberService.writeCheck(vo);
		if(check) {
			logger.info("member write true");
			
			int key = mailsender.mailSendWithUserKey(vo.getMemberMail(), vo.getMemberId(), request);
			vo.setMemberAuth(key);
			memberService.insertMember(vo);
			return "redirect:/"; //이메일 인증을 해달라는 페이지로 이동(홈으로 버튼 포함)
		} else {
			logger.info("member write false");
			
			model.addAttribute("write_msg", "failure");
			return "member/write";
		}
	}
	
	// 인증
	@RequestMapping(value = "key", method = RequestMethod.GET)
	public String keyConfirm(@RequestParam("memberId") String memberId, @RequestParam("memberKey") int key) {
		logger.info("member key");
		boolean check = memberService.authCheck(key);
		if(check) {
			logger.info("member auth true");
			
			memberService.updateAuth(memberId);
			return "redirect:/"; //인증이 되었다는 페이지로 이동
		} else {
			logger.info("member auth false");
			
			return "redirect:/"; //404
		}		
	}
	
	// 회원 등록 처리
	@RequestMapping("insert.do")
	public String memberInsert(@ModelAttribute MemberVO vo) {
		logger.info("member insert.do");
		
		return "redirect:/";
	}
	
	// 로그인 화면
	@RequestMapping("login.do")
	public String login() {
		logger.info("member login.do");
		
		return "member/login";
	}
	
	// 로그인
	@RequestMapping("login")
	public String login(MemberVO vo, HttpServletRequest req, RedirectAttributes rttr, Model model) {
		logger.info("member login");
		
		HttpSession session = req.getSession();
		MemberVO login = memberService.login(vo);
		if(login == null) {
			logger.info("member login false");
			
			session.setAttribute("member", null);
			model.addAttribute("login_msg", "failure");
			return "member/login";
		} else if (login.getMemberAuth() == 1) {
			logger.info("member login true");
			
			session.setAttribute("member", login);
			model.addAttribute("login_msg", "success");
			return "redirect:/";
		} else {
			logger.info("member login not auth");
			
			return "redirect:/"; //인증되지 않았다는 페이지
		}
	}
	
	// 로그아웃 처리
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		logger.info("member logout");
		
		session.invalidate();
		return "redirect:/";
	}
	
	
	/* 회원 정보 페이지에서 수행 */
	// 회원정보, 회원이 평가한 평균 별점, 회원 리뷰들
	@RequestMapping("info")
	public String info(PagingVO vo, HttpSession session, Model model
			,@RequestParam(value="nowPage", required=false) String nowPage) {
		logger.info("member info");
		
		// 회원 정보
		MemberVO member = (MemberVO) session.getAttribute("member");
		MemberVO member2 = memberService.getMemberInfo(member.getMemberId());
		model.addAttribute("member", member2);
		
		// 회원이 평가한 평균 별점
		double avg = reviewService.getMemberAvg(member.getMemberId());
		model.addAttribute("avg", avg*20);
		
		// 회원 리뷰들(페이징)
		int total = reviewService.countMemberReview(member.getMemberId());
		if(nowPage == null) nowPage = "1";
		vo = new PagingVO(total, Integer.parseInt(nowPage), 5);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", reviewService.selectMemberReview(member.getMemberId(), vo));		
		return "member/info";
	}
	
	// 회원정보 수정(GET)
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		logger.info("member updateMember");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		MemberVO member2 = memberService.getMemberInfo(member.getMemberId());
		model.addAttribute("member", member2);
		return "member/update";
	}
	
	// 회원정보 수정(POST)
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberVO vo, Model model, HttpSession session) {
		logger.info("member update");
		
		// 비밀번호 체크
		boolean result = memberService.checkPw(vo);
		if(result) {
			memberService.updateMember(vo);
			session.setAttribute("member", vo);
			return "redirect:/";
		}else {
			model.addAttribute("member", vo);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다");
			return  "member/update";
		}
	}
}
