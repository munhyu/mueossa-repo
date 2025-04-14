package com.smhrd.mueossa.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.mueossa.entity.TbProdImage;

@Repository
public interface ProdImageRepository extends JpaRepository<TbProdImage, String> {

	List<TbProdImage> findAllBypId(String pId);

}
