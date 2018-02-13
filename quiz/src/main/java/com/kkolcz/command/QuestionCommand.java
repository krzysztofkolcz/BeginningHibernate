package com.kkolcz.command;

import com.kkolcz.model.Question;

import javax.validation.constraints.NotNull;

public class QuestionCommand extends AbstractCommand{

    @NotNull
    private String question;
    private String rightAnswer;

    public QuestionCommand() {
    }

    public QuestionCommand(Question question) {
        this.id = question.getId();
        this.question = question.getQuestion();
        this.rightAnswer = question.getRightAnswer();
    }

    public QuestionCommand(String question, String rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public String toString() {
        return "QuestionCommand{" +
                "question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }
}
