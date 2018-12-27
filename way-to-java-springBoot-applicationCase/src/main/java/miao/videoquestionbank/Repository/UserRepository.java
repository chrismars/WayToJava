package miao.videoquestionbank.Repository;

import miao.videoquestionbank.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    User findByEmailAndPassword(String email,String password);

    User findByEmail(String email);

    User findById(Integer id);


}
