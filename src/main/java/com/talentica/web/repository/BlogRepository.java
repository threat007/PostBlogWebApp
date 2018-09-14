package com.talentica.web.repository;

import com.talentica.web.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long>{
	@Query(value = "SELECT * FROM blog WHERE status <> ?1", nativeQuery = true)
	List<Blog> findAllByStatus(String status);

	@Query(value = "SELECT * FROM blog WHERE creator_id = ?1", nativeQuery = true)
	List<Blog> findAllByCreatorId(long userId);
}
