create table questions(
   id int not null primary key,
   question varchar2(4000) not null,
   rightAnswer varchar2(4000)
);

create table answers(
   id int not null primary key,
   answer varchar2(4000) not null,
   rating number(1)
);

create table questions_answers(
    question_id int not null,
    answer_id int not null,
    primary key (question_id,answer_id),
    foreign key (question_id) references questions(id),
    foreign key (answer_id) references answers(id)
);

CREATE SEQUENCE QuestionSequence start with 1 increment by 1;
CREATE SEQUENCE AnswerSequence start with 1 increment by 1;
