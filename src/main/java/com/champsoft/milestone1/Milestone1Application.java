package com.champsoft.milestone1;

import com.champsoft.milestone1.DataAccessLayer.Department;
import com.champsoft.milestone1.DataAccessLayer.DepartmentRepository;
import com.champsoft.milestone1.DataAccessLayer.Professor;
import com.champsoft.milestone1.DataAccessLayer.ProfessorRepository;
import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Milestone1Application implements CommandLineRunner{

    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;

    public Milestone1Application(ProfessorRepository professorRepository, DepartmentRepository departmentRepository) {
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Milestone1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        // Create 10+ departments
        Department d1 = departmentRepository.save(new Department(4, "Computer Science"));
        Department d2 = departmentRepository.save(new Department(2, "Health Science"));
        Department d3 = departmentRepository.save(new Department(3, "Math Science"));
        Department d4 = departmentRepository.save(new Department(1, "Business"));
        Department d5 = departmentRepository.save(new Department(5, "Engineering"));
        Department d6 = departmentRepository.save(new Department(6, "Arts"));
        Department d7 = departmentRepository.save(new Department(7, "Sciences"));
        Department d8 = departmentRepository.save(new Department(8, "Law"));
        Department d9 = departmentRepository.save(new Department(9, "Medicine"));
        Department d10 = departmentRepository.save(new Department(10, "Architecture"));

        // Create 10+ professors
        Professor p1 = new Professor("denji@edu.qc", "Denji", "5141231234");
        Professor p2 = new Professor("skibidi@edu.qc", "Skibidi", "5141231234");
        Professor p3 = new Professor("fein@edu.qc", "Fein", "5141231234");
        Professor p4 = new Professor("mango@edu.qc", "Mango", "5141231234");
        Professor p5 = new Professor("alice@edu.qc", "Alice Johnson", "5141235555");
        Professor p6 = new Professor("bob@edu.qc", "Bob Smith", "5141236666");
        Professor p7 = new Professor("carol@edu.qc", "Carol White", "5141237777");
        Professor p8 = new Professor("david@edu.qc", "David Brown", "5141238888");
        Professor p9 = new Professor("emma@edu.qc", "Emma Davis", "5141239999");
        Professor p10 = new Professor("frank@edu.qc", "Frank Wilson", "5141230000");

        // Assign departments
        p1.setDepartment(d1);
        p2.setDepartment(d2);
        p3.setDepartment(d3);
        p4.setDepartment(d4);
        p5.setDepartment(d5);
        p6.setDepartment(d6);
        p7.setDepartment(d7);
        p8.setDepartment(d8);
        p9.setDepartment(d9);
        p10.setDepartment(d10);

        // Save all professors
        professorRepository.save(p1);
        professorRepository.save(p2);
        professorRepository.save(p3);
        professorRepository.save(p4);
        professorRepository.save(p5);
        professorRepository.save(p6);
        professorRepository.save(p7);
        professorRepository.save(p8);
        professorRepository.save(p9);
        professorRepository.save(p10);




    }

}
