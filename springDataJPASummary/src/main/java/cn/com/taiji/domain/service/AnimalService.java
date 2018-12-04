package cn.com.taiji.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.taiji.domain.Animal;
import cn.com.taiji.domain.AnimalRepository;
import cn.com.taiji.domain.Animal;

@Service
public class AnimalService {
	@Inject
	private AnimalRepository animalRepository;

	// 使用默认的新增方法CrudRepository的S extends T> S save(S entity);方法
	@Transactional(propagation = Propagation.REQUIRED)
	public Animal save(Animal animal) {
		return animalRepository.save(animal);
	}

	// 使用默认的更新方法 JpaRepository的<S extends T> S saveAndFlush(S entity);方法
	@Transactional(propagation = Propagation.REQUIRED)
	public Animal saveAndFlush(Animal animal) {
		return animalRepository.saveAndFlush(animal);
	}

	// 使用默认的查询所有的方法JpaRepository的List<T> findAll();
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Animal> findAll() {
		return animalRepository.findAll();
	}

	// 使用默认的删除方法CrudRepository的void delete(T entity);方法
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Animal animal) {
		animalRepository.delete(animal);
	}

	// 重写findOne方法
	@Transactional(propagation = Propagation.REQUIRED)
	public Animal findOne(int id) {
		return animalRepository.findOne(id);
	}

	// 重写findById方法
	@Transactional(propagation = Propagation.REQUIRED)
	public Animal findById(int id) {
		return animalRepository.findById(id);
	}

	// 创建通过id更新type方法，添加事务transaction以及modifing
	@Transactional(propagation = Propagation.REQUIRED)
	public int updateType(String type, int id) {
		return animalRepository.updateType(type, id);
	}

	// 使用拼接关键字和实体类属性的方式进行删除操作
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteByName(String name) {
		return animalRepository.deleteByName(name);
	}

	// 使用拼接关键字和实体类属性的方式进行查询操作
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Animal> findByType(String type) {
		return animalRepository.findByType(type);
	}

	// 使用默认的findAll方法进行分页操作
	@Transactional(propagation = Propagation.REQUIRED)
	public Map showPage() {
		Map map = new HashMap<>();
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		PageRequest pageable = new PageRequest(0, 3, sort);
		Page<Animal> pageList;

		Specification<Animal> spec = new Specification<Animal>() {
			@Override
			public Predicate toPredicate(Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				List<Predicate> list = new ArrayList<Predicate>();
				// 通过状态查询
				list.add(criteriaBuilder.equal(root.<String>get("type"), "狗"));

				// 通过id限定范围
				// list.add(criteriaBuilder.between(root.<Integer>get("id"), 4,7));

				// 模糊查询
				// list.add(criteriaBuilder.like(root.<String>get("dept"), "%" + "部"));

				return criteriaBuilder.and(list.toArray(new Predicate[0]));
			}
		};

		pageList = animalRepository.findAll(spec, pageable);
		map.put("total", pageList.getTotalElements());
		List<Animal> list = pageList.getContent();
		map.put("animals", list);
		return map;
	}
}
