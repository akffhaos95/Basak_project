package com.basak.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class ErrorController {
	@RequestMapping(value="/404")
	public String pageError404(HttpServletRequest request, Model model) {
		return "/error";
	}
}
