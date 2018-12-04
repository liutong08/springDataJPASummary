package cn.com.taiji.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository
		extends JpaRepository<Animal, Integer>, JpaSpecificationExecutor<Animal>, CrudRepository<Animal, Integer> {

	@Query("select a from Animal a where a.id= ?1")
	Animal findOne(Integer id);

	Animal findById(int id);

	@Modifying
	@Query("update Animal a set a.type = :type where id =:id")
	int updateType(@Param("type") String type, @Param("id") int id);

	@Modifying
	int deleteByName(String name);

	List<Animal> findByType(String type);
}
