## Hibernate configuration examples:
### websystique:
#### /src/main/resources/hibernate.cfg.xml

<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-configuration SYSTEM "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
 
 
<hibernate-configuration>
    <session-factory>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="hibernate.connection.username">myuser</property>
        <property name="hibernate.connection.password">mypassword</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/websystique</property>
        <property name="show_sql">true</property>
        <property name="format_sql">false</property>
        <mapping class="com.websystique.hibernate.model.Student"/>
        <mapping class="com.websystique.hibernate.model.Address"/>
    </session-factory>
</hibernate-configuration>

#### Hibernate Utility Class
package com.websystique.hibernate;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
public class HibernateUtil {
     
    private static final SessionFactory sessionFactory;
     
    static{
        try{
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
 
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }   
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
     
}

### BeginningHibernate:
#### /src/main/resources/hibernate.cfg.xml
<?xml version="1.0"?>
<!DOCTYPE hibernate-configuration PUBLIC
  "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
  "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="connection.driver_class">org.hsqldb.jdbc.JDBCDriver</property>
        <property name="connection.url">jdbc:hsqldb:db2;shutdown=true</property>
        <property name="connection.username">sa</property>
        <property name="connection.password"/>
        <property name="dialect">org.hibernate.dialect.HSQLDialect</property>
        <!-- Echo all executed SQL to stdout -->
        <property name="show_sql">true</property>
        <!-- Drop and re-create the database schema on startup -->
        <property name="hbm2ddl.auto">create-drop</property>
        <property name="c3p0.timeout">10</property><!-- anyd c3p0 property - With this line in the configuration, Hibernate will disable its internal connection pool and use c3p0 instead.-->
        <mapping class="chapter03.hibernate.Person"/>
        <mapping class="chapter03.hibernate.Skill"/>
        <mapping class="chapter03.hibernate.Ranking"/>
    </session-factory>
   
</hibernate-configuration>

#### SessionUtil.java
package com.websystique.hibernate;
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
public class HibernateUtil {
     
    private static final SessionFactory sessionFactory;
     
    static{
        try{
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
 
        }catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }   
    }
     
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
     
}

## One To One Uni PK 003
### SQL
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (student_id)
);
 
create table ADDRESS (
   address_id BIGINT NOT NULL,
   PRIMARY KEY (address_id),
   CONSTRAINT student_address FOREIGN KEY (address_id) REFERENCES STUDENT ( student_id) ON DELETE CASCADE 
);

### Entity
@Entity
@Table(name = "STUDENT")
public class Student{
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;
}

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name = "ADDRESS_ID")
    private long id;
}

### java
Student student = new Student("Sam","Disilva","Maths");
Address address = new Address("10 Silver street","NYC","USA");
//open session and begin transaction 
session.persist(student);
address.setId(student.getId());
student.setAddress(address);
session.save(student);
List<Student> students = (List<Student>)session.createQuery("from Student ").list();
for(Student s: students){ System.out.println(s); }
//commit transaction




## One To One Uni FKA 004
### SQL
create table ADDRESS (
   address_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (address_id)
);
 
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   home_address_id BIGINT NOT NULL,
   PRIMARY KEY (student_id),
   CONSTRAINT student_address FOREIGN KEY (home_address_id) REFERENCES ADDRESS ( address_id)
);

###Entity
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;
 
    @OneToOne
    @JoinColumn(name="HOME_ADDRESS_ID")
    private Address address;
}

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private long id;
}

### java
Student student = new Student("Sam","Disilva","Maths");
Address address = new Address("10 Silver street","NYC","USA");
//open session and begin transaction 
session.persist(address);
student.setAddress(address);
session.persist(student);
List<Student> students = (List<Student>)session.createQuery("from Student ").list();
for(Student s: students){ System.out.println(s); }
//commit transaction


## One To One Bi Shared PK 005
http://websystique.com/hibernate/hibernate-one-to-one-bidirectional-with-shared-primary-key-annotation-example/

create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (student_id)
);
 
create table ADDRESS (
   address_id BIGINT NOT NULL,
   PRIMARY KEY (address_id),
   CONSTRAINT student_address FOREIGN KEY (address_id) REFERENCES STUDENT ( student_id) ON DELETE CASCADE 
);

@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;

    @OneToOne(mappedBy="student", cascade = CascadeType.ALL)
    private Address address;
}

@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @Column(name="ADDRESS_ID")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign",parameters=@Parameter(name="property", value="student"))
    private long id;
 
    @OneToOne
    @PrimaryKeyJoinColumn
    private Student student;
}

 
Student student = new Student("Sam","Disilva","Maths");
Address address = new Address("10 Silver street","NYC","USA");
//open session and begin transaction 
student.setAddress(address);
address.setStudent(student);
session.save(student);
List<Student> students = (List<Student>)session.createQuery("from Student ").list();
for(Student s: students){ System.out.println(s); }
//commit transaction



## Many To One Uni 006
### SQL
create table UNIVERSITY (
   university_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (university_id)
);
 
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   university_id BIGINT NOT NULL,
   PRIMARY KEY (student_id),
   CONSTRAINT student_university FOREIGN KEY (university_id) REFERENCES UNIVERSITY (university_id) ON UPDATE CASCADE ON DELETE CASCADE
);


### Entity 
@Entity
@Table(name = "UNIVERSITY")
public class University {
    @Id
    @GeneratedValue
    @Column(name = "UNIVERSITY_ID")
    private long id;
}

@Entity
@Table(name = "STUDENT")
public class Student {
    @ManyToOne(optional = false)
    @JoinColumn(name="UNIVERSITY_ID")
    private University university;
}

### java
Student student1 = new Student("Sam","Disilva","Maths");
Student student2 = new Student("Joshua", "Brill", "Science");
Student student3 = new Student("Peter", "Pan", "Physics");
 
University university = new University("CAMBRIDGE", "ENGLAND");

student1.setUniversity(university);
student2.setUniversity(university);
student3.setUniversity(university);

//open session and begin transaction 

session.persist(university);
session.persist(student1);
session.persist(student2);
session.persist(student3);
 
List<Student> students = (List<Student>)session.createQuery("from Student ").list();
for(Student s: students){System.out.println(s+s.getUniversity());}
//commit transaction


## Many To One Bi 007
create table UNIVERSITY (
   university_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (university_id)
);
 
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   university_id BIGINT NOT NULL,
   PRIMARY KEY (student_id),
   CONSTRAINT student_university FOREIGN KEY (university_id) REFERENCES UNIVERSITY (university_id) ON UPDATE CASCADE ON DELETE CASCADE
);

### Entity 
@Entity
@Table(name = "UNIVERSITY")
public class University {
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> students;
}

@Entity
@Table(name = "STUDENT")
public class Student {
    @ManyToOne(optional = false)
    @JoinColumn(name = "UNIVERSITY_ID")
    private University university;
}

### java
Student student1 = new Student("Sam", "Disilva", "Maths");
Student student2 = new Student("Joshua", "Brill", "Science");
Student student3 = new Student("Peter", "Pan", "Physics");

University university = new University("CAMBRIDGE", "ENGLAND");
List<Student> allStudents = new ArrayList<Student>();

student1.setUniversity(university);
student2.setUniversity(university);
student3.setUniversity(university);

allStudents.add(student1);
allStudents.add(student2);
allStudents.add(student3);

university.setStudents(allStudents);
//open session and begin transaction 

session.persist(university);// Students will be presisted automatically, thanks to CASCADE.ALL defined on students property of University class.

List<Student> students = (List<Student>) session.createQuery( "from Student ").list();
for (Student s : students) { System.out.println(s+s.getUniversity()); }

// Note that now you can also access the relationship from University to Student
//commit transaction


## Many To Many Uni 008
### SQL
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (student_id)
);
 
create table SUBJECT (
   subject_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (subject_id)
);
 
CREATE TABLE STUDENT_SUBJECT (
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, subject_id),
    CONSTRAINT FK_STUDENT FOREIGN KEY (student_id) REFERENCES STUDENT (student_id),
    CONSTRAINT FK_SUBJECT FOREIGN KEY (subject_id) REFERENCES SUBJECT (subject_id)
);

### Entity
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;
 
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_SUBJECT", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID") })
    private List<Subject> subjects = new ArrayList<Subject>();
}
 
@Entity
@Table(name = "SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID")
    private long id;
}

 
### java
Student student1 = new Student("Sam","Disilva");
Student student2 = new Student("Joshua", "Brill");
 
Subject subject1 = new Subject("Economics");
Subject subject2 = new Subject("Politics");
Subject subject3 = new Subject("Foreign Affairs");

//Student1 have 3 subjects
student1.getSubjects().add(subject1);
student1.getSubjects().add(subject2);
student1.getSubjects().add(subject3);
 
//Student2 have 2 subjects
student2.getSubjects().add(subject1);
student2.getSubjects().add(subject2);

//open session and begin transaction 

session.persist(student1);
session.persist(student2);
 
//commit transaction

## Many To Many Bi 009
### SQL
http://websystique.com/hibernate/hibernate-many-to-many-bidirectional-annotation-example/
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (student_id)
);
 
create table SUBJECT (
   subject_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (subject_id)
);
 
CREATE TABLE STUDENT_SUBJECT (
    student_id BIGINT NOT NULL,
    subject_id BIGINT NOT NULL,
    PRIMARY KEY (student_id, subject_id),
    CONSTRAINT FK_STUDENT FOREIGN KEY (student_id) REFERENCES STUDENT (student_id),
    CONSTRAINT FK_SUBJECT FOREIGN KEY (subject_id) REFERENCES SUBJECT (subject_id)
);

### Entity
@Entity
@Table(name = "STUDENT")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID")
    private long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STUDENT_SUBJECT", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID") })
    private List<Subject> subjects = new ArrayList<Subject>();
}

@Entity
@Table(name = "SUBJECT")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "SUBJECT_ID")
    private long id;
 
    /* Only change between ManyToMany Unidirectional and Bidirectional */
    @ManyToMany(mappedBy="subjects")
    private List<Student> students = new ArrayList<Student>();
}

### java
Student student1 = new Student("Sam","Disilva");
Student student2 = new Student("Joshua", "Brill");

Subject subject1 = new Subject("Economics");
Subject subject2 = new Subject("Politics");
Subject subject3 = new Subject("Foreign Affairs");

//Student1 have 3 subjects
student1.getSubjects().add(subject1);
student1.getSubjects().add(subject2);
student1.getSubjects().add(subject3);

//Student2 have 2 subjects
student2.getSubjects().add(subject1);
student2.getSubjects().add(subject2);

//open session and begin transaction 

session.persist(student1);
session.persist(student2);

//commit transaction
