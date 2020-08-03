package com.basak.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basak.app.model.MemberVO;
import com.basak.app.model.PagingVO;
import com.basak.app.model.RecommendVO;
import com.basak.app.service.IRecommendService;

@Controller
public class RecommendController {
	
	private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);
	
	@Autowired
	IRecommendService recommendService;
	
	/* 로그인 권한에서 동작 */
	@RequestMapping(value = "/home/ajax", produces="application/json; charset=utf8",method= RequestMethod.POST)
	public @ResponseBody String insertRecommend(HttpSession session, @RequestBody RecommendVO recommend, PagingVO vo, Model model
			,@RequestParam(value="nowPage", required=false) String nowPage) {
		logger.info("recommend insert");
		
		MemberVO member = (MemberVO) session.getAttribute("member");
		//검사용
		System.out.println("check.comment : "+recommend.getComment()+ "\n멤버 id : "+recommend.getmemberId());

		recommend.setmemberId(member.getMemberId());
		recommendService.insertRecommend(recommend);
		
		JSONArray arr = new JSONArray(); 
		
		int total = recommendService.countRecommend();
		if(nowPage == null) nowPage = "1";
		vo = new PagingVO(total, Integer.parseInt(nowPage), 5); 
		List<RecommendVO> viewAll = recommendService.selectRecommend(vo);
		System.out.println(viewAll);
		
		for(int i = viewAll.size()-1 ; i >-1 ; i--) {
	    	JSONObject obj = new JSONObject();
	    	obj.put("recommendId", viewAll.get(i).getRecommendId());
	    	obj.put("comment", viewAll.get(i).getComment());
	    	obj.put("memberId", viewAll.get(i).getmemberId());
	    	arr.add(obj);
	    }
		logger.info("");
	    return arr.toJSONString();
	}

	/* 로그인 권한에서 동작 */
	@RequestMapping("/recommend/delete/{recommendId}")
	public String deleteRecommend(@PathVariable int recommendId, HttpSession session, Model model) {
		logger.info("recommend delete");
		
		RecommendVO recommend = new RecommendVO();
		MemberVO member = (MemberVO) session.getAttribute("member");
		recommend.setmemberId(member.getMemberId());
		recommend.setRecommendId(recommendId);
		recommendService.deleteRecommend(recommend);
		return "redirect:/";
	}
	
	/* 미사용 : 로그인 권한에서 동작 */
	@RequestMapping("/recommend/update.do")
	public String updateRecommend(HttpSession session, int recommendId, Model model) {
		logger.info("recommend update");
		return "redirect:/";
	}
}//end class