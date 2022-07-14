package com.example.springboottodoapp.persistence;

import com.example.springboottodoapp.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findByUsername(String username);
    
// 쿼리문이 복잡해지면 이와 같이 어노테이션을 적용하여 사용함 ?1은 매개변수의 순서 위치
//    @Query("select * from Todot where t.username = ?1")
//    List<TodoEntity> findByUsername(String username);
}
