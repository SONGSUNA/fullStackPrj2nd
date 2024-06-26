package com.office.ticketreserve.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.office.ticketreserve.user.UserDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/review/")
@Log4j2
public class reviewController {
	
	@Autowired
	ReviewService reviewService;
	

	@ResponseBody
	@PostMapping("/review_write")
	public ReviewDto reviewWrite(@RequestParam("rv_txt") String rv_txt, @RequestParam("rv_score") int rv_score,@RequestParam("p_id") String p_id,HttpSession session) {
		log.info("review_write");
		UserDto loginedUserDto=(UserDto) session.getAttribute("loginedUserDto");
		
		String u_id = loginedUserDto.getU_id();

		return reviewService.reviewWrite(rv_txt,rv_score,p_id,u_id);
	}
	
	@ResponseBody
	@GetMapping("/check_user_review")
	public boolean checkUserReview(@RequestParam("u_id") String u_id, @RequestParam("p_id") String p_id) {
		
		boolean hasReview = reviewService.hasUserReviewedPerformance(u_id, p_id);
		
		return hasReview;
	}
	
	
	@ResponseBody
	@PostMapping("/review_modify")
	public ReviewDto reviewModify(@RequestParam("rv_no") int rv_no) {
		log.info("review_modify");
		
		ReviewDto reviewDto = reviewService.reviewModify(rv_no);
		
		return reviewDto;
	}
	
	
	@PostMapping("/review_modify_confirm")
	@ResponseBody
	public ReviewDto reviewModifyConfirm(@RequestParam("rv_txt") String rv_txt,
	                                  @RequestParam("rv_score") int rv_score,
	                                  @RequestParam("rv_no") int rv_no,
	                                  @RequestParam("p_id") String p_id) {
	    log.info("review_modify_confirm");
	    ReviewDto reviewDto = reviewService.reviewModifyConfirm(rv_txt, rv_score, rv_no);
	    
	    log.info(reviewDto);
	    
	    return reviewDto != null ? reviewDto : null;
	}
	

	@ResponseBody
	@PostMapping("/review_delete_confirm")
	public int reviewDeleteConfirm(@RequestParam("rv_no") int rv_no) {
	    log.info("review_delete_confirm");
	    int result = reviewService.reviewDeleteConfirm(rv_no);
	    
	    return result;
	}
	

}
