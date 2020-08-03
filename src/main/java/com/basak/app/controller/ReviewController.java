package com.basak.app.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.basak.app.model.MemberVO;
import com.basak.app.model.ReviewVO;
import com.basak.app.service.IReviewService;

@Controller
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	IReviewService reviewService;
	
	/* 로그인 권한에서 이루어 져야하는 동작 */
	@RequestMapping(value="/review/write.do")
	public String insertReview(HttpSession session, @ModelAttribute ReviewVO review, Model model) {
		logger.info("review insert");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		review.setMemberId(member.getMemberId());
		reviewService.insertReview(review);
		return "redirect:/snack/info/"+review.getSnackId();
	}

	/* 로그인 권한에서 이루어 져야하는 동작 */
	@RequestMapping("/review/delete/{reviewId}")
	public String deleteEmp(HttpSession session, @PathVariable int reviewId, Model model) {
		logger.info("review delete");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		int snackId = reviewService.getSnackId(reviewId);
		
		ReviewVO review = new ReviewVO();
		review.setMemberId(member.getMemberId());
		review.setReviewId(reviewId);
		reviewService.deleteReview(review);
		return "redirect:/snack/info/"+snackId;
	}
	
	/* 미사용 : 로그인 권한에서 이루어 져야하는 동작 */
	@RequestMapping(value="/review/update", method=RequestMethod.POST)
	public String updateReview(HttpSession session, ReviewVO review, Model model) {
		logger.info("review update");
		
		reviewService.updateReview(review);
		return "redirect:/"+review.getReviewId();
	}
}//end class