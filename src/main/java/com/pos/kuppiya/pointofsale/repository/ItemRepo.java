package com.pos.kuppiya.pointofsale.repository;


import com.pos.kuppiya.pointofsale.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
@Transactional
public interface ItemRepo extends JpaRepository <Item, Integer> {

    @Modifying
    @Query(value = "update item set balance_qty=?1, selling_price=?2 where item_id=?3",nativeQuery = true)
    void updateBalQtySellingPrice(double balanceQty, double sellingPrice, int id);

    List<Item> findAllByActiveStateEquals(boolean status);

    @Modifying
    @Query(value = "update item set item_name=?1, balance_qty=?2, active_state=?3 where item_id=?4", nativeQuery = true)
    void updateNameBalQtyState(String itemName, double balanceQty, boolean activeState,int id);

    int countAllByActiveStateEquals(boolean b);

    Page<Item> findAllByActiveStateEquals(boolean status, Pageable pageable);

}
