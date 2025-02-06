package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ILevelDao;
import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IStudentDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Career;
import com.roche.SchoolRegister.SchoolRegister.entities.Level;
import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICareerService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILevelService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentDaoImpl {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private IStudentDao studentDao;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private ICareerService careerService;

    @PersistenceContext
    private EntityManager em;

    public Student save(Student student){
        return this.studentDao.save(student);
    }

    public List<Student> findAll(){
        return (List<Student>) this.studentDao.findAll();
    }

    public void delete(Student student){
        this.studentDao.delete(student);
    }

    public Optional<Student> findById(Long id){
        return this.studentDao.findById(id);
    }

    public Student findByDna(String dna){
        return (Student) this.em.createQuery(IStudentDao.findByDna)
                .setParameter("dna", dna)
                .getSingleResult();
    }

    public List<Student> findByQuery(HttpServletRequest request){

        Level level = (request.getParameter("level").trim().isEmpty()) ? null : this.levelService.findById(Long.parseLong(request.getParameter("level"))).orElse(null);
        Career career = (request.getParameter("career").trim().isEmpty()) ? null : this.careerService.findById(Long.parseLong(request.getParameter("career"))).orElse(null);
        String name = request.getParameter("name").trim();
        String status = request.getParameter("status").trim();


        StringBuilder query = new StringBuilder("SELECT s FROM Student s WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if(!name.isEmpty()){
            query.append(" AND LOWER(s.name) = LOWER(:name)");
            params.put("name", name);
        }
        if(!status.isEmpty()){
            query.append(" AND LOWER(s.status) = LOWER(:status)");
            params.put("status", status);
        }
        if(level != null){
            query.append(" AND s.level = :level");
            params.put("level", level);
        }
        if(career != null){
            query.append(" AND s.career = :career");
            params.put("career", career);
        }

        if(params.isEmpty()){
            log.info(MessageConstant.EMPTY_PARAMS.name().concat(" ").concat(query.toString()));
            return new ArrayList<>();
        }

        TypedQuery<Student> consult = this.em.createQuery(query.toString(), Student.class);
        params.forEach(consult::setParameter);

        List<Student> students = consult.getResultList();

        log.info(students.toString());
        return  students;
    }

}
