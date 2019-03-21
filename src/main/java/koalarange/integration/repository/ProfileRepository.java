package koalarange.integration.repository;

import koalarange.integration.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    @Autowired
    List<Profile> findAll();

    Profile findByEmail(String email);

}
