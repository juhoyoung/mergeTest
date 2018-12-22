package com.team.login.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.login.DTO.LoginDTO;
import com.team.login.DTO.MemberVO;
import com.team.login.Service.LoginService;

@Controller
public class loginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(@ModelAttribute("ldto") LoginDTO ldto, Model model){
		model.addAttribute("loginForm", ldto);
	}
	
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	public String loginSuccess(@ModelAttribute("LoginDTO") @Valid LoginDTO ldto, Model model, BindingResult result, HttpServletRequest request)throws Exception{
		MemberVO member = new MemberVO();
		HttpSession session = request.getSession();
		member = loginService.login(ldto);
		
		System.out.println(ldto.getID() + " " + ldto.getPWD());
		if (member != null && !member.getID().equals("") && !member.getPWD().equals("")) {
			session.setAttribute("member", member);
			return "main";
		}else {
			model.addAttribute("msg", "사용자의 아이디 혹은 비밀번호가 일치하지 않습니다.");
			return "redirect:login";
		}
	}
}
