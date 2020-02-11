package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("/hello2")
	public String hello2(@RequestParam(value = "name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello2";
	}
	
	@RequestMapping(value= {"/hello2", "hello2/{name}"})
	public String helloPath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		} else {
			model.addAttribute("name", "Phoenix");
		}
		return "hello2";
	}
	
	@RequestMapping("/calcuConvert")
	public String calcuConvert(@RequestParam(value = "num1") String num1, @RequestParam (value = "num2") String num2, Model model) {
		
		String[] number = {"", "satu", "dua", "tiga", "empat", "lima", "enam", "tujuh", "delapan", "sembilan", "sepuluh", "sebelas"};
		
		if (num1 != null && num2 != null) {
			model.addAttribute("num1", num1);
			model.addAttribute("num2", num2);
			int amount = Integer.parseInt(num1) + Integer.parseInt(num2);
			if (amount < 12) {
				model.addAttribute("var", number[(int)amount]);
			}
			if (amount >= 12 && amount <= 19) {
				model.addAttribute("var", number[(int)amount%10] + " belas");
			}
			if (amount >= 20 && amount <= 99) {
				model.addAttribute("var", number[(int)amount/10] + " puluh " + number[(int)amount%10]);
			}
			if (amount == 100) {
				model.addAttribute("var", "seratus");
			}
		}
		return "calcuConvert";
	}
}