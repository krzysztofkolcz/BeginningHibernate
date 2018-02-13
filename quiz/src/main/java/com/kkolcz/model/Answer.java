package com.kkolcz.model;

import com.kkolcz.command.AnswerCommand;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer extends AbstractModel<AnswerCommand>{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "AnswerSequence")
    @SequenceGenerator(name="AnswerSequence", sequenceName="AnswerSequence", allocationSize = 1)
    //CREATE SEQUENCE AnswerSequence start with 1 increment by 1;
    private int id;

    @Column
    private String answer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "questions_answers",
            joinColumns = { @JoinColumn(name = "answer_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") })
    private Question question;

    @Override
    public void fillDataFromCommandObject(AnswerCommand command) {
        this.answer = command.getAnswer();
    }

    @Override
    public boolean isNew() {
        return false;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
