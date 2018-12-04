# springDataJPASummary
springDataJPASummary
springData JPA
	
	1、使用默认的方法
	
		1.使用默认的新增方法CrudRepository的S extends T> S save(S entity);方法
		2.使用默认的更新方法 JpaRepository的<S extends T> S saveAndFlush(S entity);方法
		3.使用默认的查询所有的方法JpaRepository的List<T> findAll();
		4.使用默认的删除方法CrudRepository的void delete(T entity);方法
		5.使用默认的findAll方法进行分页操作
		
	2、重写已有的方法
	
		1.重写findOne方法
		2.重写findById方法等
		
	3、创建方法@query() :?1 :别名
	
		创建通过id更新type方法，添加事务transaction以及modifing
		
	4、使用拼接关键字和实体类属性的方法，不需要写@Query()
	
		1.deleteById,deleteByName,deleteByType等进行删除操作
		2.findByType,findByName等进行查询操作
		
	5、注意：
		
		1.在application.properties中对数据库的连接属性和jpa进行配置，在pom.xml文件中导入所需要的依赖
		2.对于Service层添加Service注解，使用@Inject注入Repository接口，在测试类中使用@Inject注入Service
		3.对于update和delete事务，需要添加@Modifing注解，并在Service层添加事务注解@Transactional(propagation = Propagation.REQUIRED)
