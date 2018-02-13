package com.kkolcz.controller;


import java.util.List;

import com.kkolcz.model.Question;
import com.kkolcz.command.QuestionCommand;
import com.kkolcz.service.QuestionService;
import com.kkolcz.validator.QuestionFormValidator;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(QuestionsController.class);
    final static Logger logg = Logger.getLogger(QuestionsController.class);

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionFormValidator questionFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(questionFormValidator);
	}

    @RequestMapping(value="/questionList", method = RequestMethod.GET)
    public String showQuestionList(ModelMap model){
        logger.debug("showQuestionList()");
        logger.info("showQuestionList()");
        logger.warn("showQuestionList()");
        logger.error("showQuestionList()");
        logg.debug("showQuestionList()");
        logg.info("showQuestionList()");
        logg.warn("showQuestionList()");
        logg.error("showQuestionList()");
        System.out.println("sout showQuestionList()");
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList",questionList);
        return "questions/questionList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(
            @ModelAttribute("questionForm") @Validated QuestionCommand questionCommand,
            BindingResult result,
            Model model,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "questions/questionForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            if(questionCommand.isNew()){
                redirectAttributes.addFlashAttribute("msg", "Question added successfully!");
            }else{
                redirectAttributes.addFlashAttribute("msg", "Question updated successfully!");
            }
            Question question = new Question(questionCommand);
            questionService.saveOrUpdate(question);
            return "redirect:/questions/questionList";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        QuestionCommand command = new QuestionCommand();
        model.addAttribute("question",command);
        return "questions/questionForm";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        logger.debug("showUpdateQuestionForm() : {}", id);
        Question question = questionService.findById(id);
        QuestionCommand command = new QuestionCommand(question);
        model.addAttribute("question", command);
        return "questions/questionForm";
    }

    @RequestMapping(value = "/aaa/{id}/delete", method = {RequestMethod.POST,RequestMethod.GET})
    public String delete(@PathVariable("id") int id,
                             final RedirectAttributes redirectAttributes) {
        logger.debug("deleteQuestion() : {}", id);
        questionService.delete(id);
        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Question is deleted!");
        return "redirect:/questions/questionList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") int id,
                               ModelMap model){
        Question question = questionService.findById(id);
        QuestionCommand command = new QuestionCommand(question);
        if(question == null){
            model.addAttribute("message","not found");
        }
        model.addAttribute("question",command);
        return "/questions/questionDetails";
    }


}
