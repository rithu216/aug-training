package qapitol.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qapitol.demo.app.entity.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students,Integer> {

}
