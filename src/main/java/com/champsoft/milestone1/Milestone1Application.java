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
        Department d1 = departmentRepository.save(new Department(4,"Computer Science"));
        Department d2 = departmentRepository.save(new Department(2,"Health Science"));
        Department d3 = departmentRepository.save(new Department(3,"Math Science")) ;
        Department d4 = departmentRepository.save(new Department(1,"Business")) ;

        Professor p1 = professorRepository.save(new Professor("denji@edu.qc", "Denji", "5141231234"));
        Professor p2 = professorRepository.save(new Professor("skibidi@edu.qc", "Skibidi", "5141231234"));
        Professor p3 = professorRepository.save(new Professor("fein@edu.qc", "Fein", "5141231234"));
        Professor p4 = professorRepository.save(new Professor("mango@edu.qc", "Mango", "5141231234"));

      
        p1.setDepartment(d1);
        p2.setDepartment(d2);
        p3.setDepartment(d3);
        p4.setDepartment(d4);


        professorRepository.save(p1);
        professorRepository.save(p2);
        professorRepository.save(p3);
        professorRepository.save(p4);




    }

}
