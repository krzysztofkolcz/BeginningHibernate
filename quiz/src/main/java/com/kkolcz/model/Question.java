package com.kkolcz.model;

import com.kkolcz.command.QuestionCommand;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="questions")
public class Question extends AbstractModel<QuestionCommand>{

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Question.class);

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "QuestionSequence")
    @SequenceGenerator(name="QuestionSequence", sequenceName="QuestionSequence", allocationSize = 1)
    //CREATE SEQUENCE QuestionSequence start with 1 increment by 1;
    private Integer id;
    @Column
    private String question;
    @Column
    private String rightAnswer;


    @OneToMany(fetch = FetchType.EAGER,mappedBy="question")
    private List<Answer> answers = new ArrayList<Answer>();

    public Question(QuestionCommand command){
        this.id = command.getId();
        this.question = command.getQuestion();
        this.rightAnswer = command.getRightAnswer();
        logger.debug(command.toString());
        System.out.println(command);
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question1 = (Question) o;

        if (getId() != question1.getId()) return false;
        return getQuestion() != null ? getQuestion().equals(question1.getQuestion()) : question1.getQuestion() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getQuestion() != null ? getQuestion().hashCode() : 0);
        return result;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public void fillDataFromCommandObject(QuestionCommand command) {
        this.id = command.getId();
        this.question = command.getQuestion();
        this.rightAnswer = command.getRightAnswer();
        logger.debug(command.toString());
        System.out.println(command);
    }
}
