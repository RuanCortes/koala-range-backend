package koalarange.integration.repository;

import koalarange.integration.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComentariosRepository extends CrudRepository<Comment, Integer> {

    @Autowired
    List<Comment> findAll();

}
