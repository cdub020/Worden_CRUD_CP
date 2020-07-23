package com.galvanize.crudcheckpoint;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface UserRepository extends CrudRepository<User,Long> {
        User findByEmail(String email);
}
