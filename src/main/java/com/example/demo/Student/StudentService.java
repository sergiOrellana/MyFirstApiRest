package com.example.demo.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student s) {
        Optional<Student> Student = studentRepository.findStudentByEmail(s.getEmail());
        if(Student.isPresent())
        {
            throw new IllegalStateException("email taken");
        }
        else
        {
            studentRepository.save(s);
        }
        System.out.println(s.toString());
    }


    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id))
        {
            throw new IllegalStateException("Student with id " + id + " does not exist");
        }
        else
        {
            studentRepository.deleteById(id);
        }
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        if(studentRepository.existsById(id))
        {
           Optional<Student> s = studentRepository.findById(id);
           Student st= s.get();

           if(name != null && name.length()>0 && !Objects.equals(st.getName(), name))
               st.setName(name);

           if(email != null && email.length()>0 && !Objects.equals(st.getEmail(), email))
           {
               Optional<Student> s2 = studentRepository.findStudentByEmail(email);
               if(s2.isPresent())
               {
                   throw new IllegalStateException("E-mail taken");
               }
               st.setEmail(email);
           }



        }
        else
        {
            throw new IllegalStateException("The student with id " + id + " does not exist");
        }
    }
}
