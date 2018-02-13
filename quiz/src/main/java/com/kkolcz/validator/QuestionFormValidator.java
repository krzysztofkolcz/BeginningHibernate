package com.kkolcz.validator;

import com.kkolcz.command.QuestionCommand;
import com.kkolcz.model.Question;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class QuestionFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return QuestionCommand.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuestionCommand question = (QuestionCommand)o;
        if(question.getQuestion() == null || question.getQuestion().length()==0){
            errors.rejectValue("question", "NotEmpty.questionForm.question");
        }
    }
}
