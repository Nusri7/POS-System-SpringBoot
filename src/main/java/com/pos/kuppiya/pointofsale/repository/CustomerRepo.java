package com.pos.kuppiya.pointofsale.repository;

import com.pos.kuppiya.pointofsale.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
@Transactional

public interface CustomerRepo extends JpaRepository<Customer,Integer> {

    List<Customer> findAllByCustomerNameEquals(String customerName);


    Optional<Customer> findAllByNICEquals(String nic);

    @Modifying
    @Query(value = "update customer set customer_name=?1, customer_address=?2, nic=?3 where customer_id=?4",nativeQuery = true)
    void updateCustomerbyid(String customerName, String customerAddress,String NIC ,int id);

   Customer findByActiveStateEqualsAndCustomerIdEquals(boolean b,int id);
    List<Customer> findAllByActiveStateEquals(boolean b);
    
}
