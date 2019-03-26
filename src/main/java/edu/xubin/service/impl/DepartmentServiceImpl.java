package edu.xubin.service.impl;

import edu.xubin.bean.DepartmentEntity;
import edu.xubin.dao.DepartmentDao;
import edu.xubin.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentEntity findByDepartmentid(Integer departmentid) {
        return departmentDao.findAllByDepartmentid(departmentid);
    }
}
