package com.smhrd.mueossa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbProduct;

@Repository
public interface ProductRepository extends JpaRepository<TbProduct, String> {

}
