## One To One Uni PK
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




## One To One Uni FKA 
### SQL
create table ADDRESS (
   address_id BIGINT NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (address_id)
);
 
create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
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
