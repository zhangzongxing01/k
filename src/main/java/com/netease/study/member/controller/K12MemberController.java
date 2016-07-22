package com.netease.study.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.study.member.domain.K12Member;
import com.netease.study.member.logic.K12MemberLogic;

@Controller
public class K12MemberController {

	@Resource
	private K12MemberLogic k12MemberLogic;

	@RequestMapping(value = "/test/K12MemberController/login.do")
	// @ResponseBody String userName,String userPWD
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model, K12Member k12Member) {
		// String userName = request.getParameter("userName");
		// String pass = request.getParameter("userPWD");
		System.out.println("hhhh");
		if (k12MemberLogic.login(k12Member.getUserName(),
				k12Member.getUserPWD())) {
			model.addAttribute("hello", "nihao");
			return "hello";

		}
		return "error";

	}

//	@RequestMapping(value = "/test/K12MemberController/login.do")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,
			HttpServletResponse response, String userName, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 0);
		result.put("message", "请求参数错误，请核对信息");
		return result;

	}

	public static void main(String[] args) {
		K12MemberLogic k12MemberLogic = new K12MemberLogic();
		if (k12MemberLogic.login("11", "11")) {
			System.out.println("success");
		}
		System.out.println("error");
	}
}
