package ait.student.nutrition.rep;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ait.student.nutrition.nutrition.dto.supplier;



public interface NutritionRepository extends JpaRepository<supplier, Long> {
	
	Optional<supplier> findById(Long id);
	@Query(value = "SELECT * from supplier u WHERE u.item_code= :item_code",nativeQuery=true)
	List<supplier> findByItemCode(@Param("item_code")String item_code);
	@Query(value = "SELECT * from supplier u WHERE u.supplier_name= :supplier_name",nativeQuery=true)
	List<supplier> findBySupplier(@Param("supplier_name")String item_code);
	List<supplier> findByItem(String item);
}
 