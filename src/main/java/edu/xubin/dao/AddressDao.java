package edu.xubin.dao;

import edu.xubin.bean.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<AddressEntity, Integer>{
    AddressEntity findByAddressid(Integer addressid);
}
