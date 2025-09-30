package com.example.demo.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.answer.AnswerForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.ResponseBody;

/*  
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionRepository questionRepository; //리포지터리 사용
	
	//@ResponseBody
	@GetMapping("/question/list")
	public String list(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questionList", questionList);
		return "question_list"; // 전달할 페이지 명
	}
}
*/


@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController{
	private final QuestionService questionService; // 서비스 사용
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList",questionList);
		return "question_list";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String create(QuestionForm questionForm) {
		return "question_form";
	}
	
//	@PostMapping("/create")
//	public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
//		this.questionService.create(subject, content);
//		return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
//	}
	
	// 입력값 검사 추가
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		this.questionService.create(questionForm.getSubject(), questionForm.getContent());
		return "redirect:/question/list";
	}
}