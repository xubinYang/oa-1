package edu.xubin.dao;

import edu.xubin.bean.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<DepartmentEntity, Integer> {
    DepartmentEntity findAllByDepartmentid(Integer departmentid);
}
