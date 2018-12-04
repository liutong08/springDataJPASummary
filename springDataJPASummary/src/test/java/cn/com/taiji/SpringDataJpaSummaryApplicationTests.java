package cn.com.taiji;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.taiji.domain.Animal;
import cn.com.taiji.domain.service.AnimalService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJpaSummaryApplicationTests {

	@Inject
	private AnimalService animalService;

	@Test
	@Ignore
	public void contextLoads() {
	}

	// 使用默认的新增方法CrudRepository的S extends T> S save(S entity);方法
	@Test
	public void save() {
		Animal animal = new Animal();
		animal.setName("折耳猫");
		animal.setType("猫");
		Animal a = animalService.save(animal);
		System.out.println(a.toString());
	}

	// 重写findOne方法
	@Test
	public void findOne() {
		Animal animal = animalService.findOne(1);
		System.err.println(animal.toString());
	}

	// 重写findById方法
	@Test
	public void findById() {
		Animal animal = animalService.findById(2);
		System.err.println(animal.toString());
	}

	// 使用默认的更新方法 JpaRepository的<S extends T> S saveAndFlush(S entity);方法
	@Test
	public void saveAndFlush() {
		Animal animal = animalService.findById(3);
		animal.setName("单身");
		Animal animal2 = animalService.saveAndFlush(animal);
		System.err.println(animal2.toString());
	}

	// 创建通过id更新type方法，添加事务transaction以及modifing
	@Test
	public void updateByType() {
		int i = animalService.updateType("猫", 1);
		System.err.println(i);
	}

	// 使用拼接关键字和实体类属性的方式进行删除操作
	@Test
	public void deleteByName() {
		int i = animalService.deleteByName("阿拉斯加");
		System.err.println(i);
	}

	// 使用CrudRepository的void delete(T entity);方法
	@Test
	public void delete() {
		Animal animal = animalService.findById(11);
		animalService.delete(animal);
	}

	// 使用默认的查询所有的方法JpaRepository的List<T> findAll();
	@Test
	public void findAll() {
		List<Animal> list = animalService.findAll();
		System.err.println(list.toString());
	}

	// 使用拼接关键字和实体类属性的方式进行查询操作
	@Test
	public void findByType() {
		List<Animal> list = animalService.findByType("猫");
		System.err.println(list.toString());
	}

	// 使用默认的findAll方法进行分页操作
	@Test
	public void showPage() {
		Map page = animalService.showPage();
		System.err.println(page.toString());
	}
}
