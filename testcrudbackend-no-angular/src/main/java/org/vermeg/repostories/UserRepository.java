package org.vermeg.repostories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.vermeg.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{


}
