package com.kkolcz.controller;

import java.util.List;

import com.kkolcz.model.Answer;
import com.kkolcz.model.Question;
import com.kkolcz.command.QuestionCommand;
import com.kkolcz.service.AnswerService;
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
@RequestMapping("/answers")
public class AnswersController {
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;

    /*
    1. formularz dla każdego pytania
    2. jeden formularz na dole strony, uzupełniany javascriptowo o id pytania oraz o odpowiedź
    3. ajaxowe wysyłanie danych do kontrolera
     */

    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrUpdate(
            @RequestParam("question_id") String questionId,
            @RequestParam("answer") String answer,
            BindingResult result,
            Model model,
            final RedirectAttributes redirectAttributes) {
        //TODO - validate question_id, answer
        Question question = questionService.findById(Integer.parseInt(questionId));
        Answer answerEntity = new Answer();
        answerEntity.setAnswer(answer);
        answerEntity.setQuestion(question);
        answerService.saveOrUpdate(answerEntity);
        return "ok";
    }
}
