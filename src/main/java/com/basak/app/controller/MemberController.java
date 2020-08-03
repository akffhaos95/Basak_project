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
	
	// ȸ�� ���
	@RequestMapping("list.do")
	public String memberList(Model model) {
		logger.info("member list.do");
		
		List<MemberVO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/list";
	}
	
	// ȸ�� ���
	@RequestMapping("write.do")
	public String memberWrite() {
		logger.info("member write.do");
		
		return "member/write";
	}
	
	// ȸ�� ��� üũ(���̵� �ߺ� üũ, ���� ����, ����Ű)
	@RequestMapping("writeCheck.do")
	public String writeCheck(@ModelAttribute MemberVO vo, Model model, HttpServletRequest request) {
		logger.info("member writeCheck.do");
		
		boolean check = memberService.writeCheck(vo);
		if(check) {
			logger.info("member write true");
			
			int key = mailsender.mailSendWithUserKey(vo.getMemberMail(), vo.getMemberId(), request);
			vo.setMemberAuth(key);
			memberService.insertMember(vo);
			return "redirect:/"; //�̸��� ������ �ش޶�� �������� �̵�(Ȩ���� ��ư ����)
		} else {
			logger.info("member write false");
			
			model.addAttribute("write_msg", "failure");
			return "member/write";
		}
	}
	
	// ����
	@RequestMapping(value = "key", method = RequestMethod.GET)
	public String keyConfirm(@RequestParam("memberId") String memberId, @RequestParam("memberKey") int key) {
		logger.info("member key");
		boolean check = memberService.authCheck(key);
		if(check) {
			logger.info("member auth true");
			
			memberService.updateAuth(memberId);
			return "redirect:/"; //������ �Ǿ��ٴ� �������� �̵�
		} else {
			logger.info("member auth false");
			
			return "redirect:/"; //404
		}		
	}
	
	// ȸ�� ��� ó��
	@RequestMapping("insert.do")
	public String memberInsert(@ModelAttribute MemberVO vo) {
		logger.info("member insert.do");
		
		return "redirect:/";
	}
	
	// �α��� ȭ��
	@RequestMapping("login.do")
	public String login() {
		logger.info("member login.do");
		
		return "member/login";
	}
	
	// �α���
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
			
			return "redirect:/"; //�������� �ʾҴٴ� ������
		}
	}
	
	// �α׾ƿ� ó��
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		logger.info("member logout");
		
		session.invalidate();
		return "redirect:/";
	}
	
	
	/* ȸ�� ���� ���������� ���� */
	// ȸ������, ȸ���� ���� ��� ����, ȸ�� �����
	@RequestMapping("info")
	public String info(PagingVO vo, HttpSession session, Model model
			,@RequestParam(value="nowPage", required=false) String nowPage) {
		logger.info("member info");
		
		// ȸ�� ����
		MemberVO member = (MemberVO) session.getAttribute("member");
		MemberVO member2 = memberService.getMemberInfo(member.getMemberId());
		model.addAttribute("member", member2);
		
		// ȸ���� ���� ��� ����
		double avg = reviewService.getMemberAvg(member.getMemberId());
		model.addAttribute("avg", avg*20);
		
		// ȸ�� �����(����¡)
		int total = reviewService.countMemberReview(member.getMemberId());
		if(nowPage == null) nowPage = "1";
		vo = new PagingVO(total, Integer.parseInt(nowPage), 5);
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", reviewService.selectMemberReview(member.getMemberId(), vo));		
		return "member/info";
	}
	
	// ȸ������ ����(GET)
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateMember(HttpSession session, Model model) {
		logger.info("member updateMember");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		MemberVO member2 = memberService.getMemberInfo(member.getMemberId());
		model.addAttribute("member", member2);
		return "member/update";
	}
	
	// ȸ������ ����(POST)
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@ModelAttribute MemberVO vo, Model model, HttpSession session) {
		logger.info("member update");
		
		// ��й�ȣ üũ
		boolean result = memberService.checkPw(vo);
		if(result) {
			memberService.updateMember(vo);
			session.setAttribute("member", vo);
			return "redirect:/";
		}else {
			model.addAttribute("member", vo);
			model.addAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�");
			return  "member/update";
		}
	}
}
