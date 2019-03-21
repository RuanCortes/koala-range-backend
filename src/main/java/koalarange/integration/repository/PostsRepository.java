package koalarange.integration.repository;

import koalarange.integration.entity.Category;
import koalarange.integration.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends CrudRepository<Post, Integer> {

    @Autowired
    List<Post> findAll();

    @Query("select p from Post p" +
            " where" +
            " (:CATEGORY IS NULL OR p.category = :CATEGORY)" +
            " AND (:RANGE IS NULL OR p.category = :RANGE)" +
            " AND (:DURATION IS NULL OR p.category = :DURATION)" +
            "order by p.createDate desc")
    List<Post> findByFilter(@Param("CATEGORY") Category categoryId,
                            @Param("RANGE") Integer range,
                            @Param("DURATION") Long duration);

}
