package koalarange.integration.repository;

import koalarange.integration.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriasRepository extends CrudRepository<Category, Integer> {

    @Autowired
    List<Category> findAll();

}
