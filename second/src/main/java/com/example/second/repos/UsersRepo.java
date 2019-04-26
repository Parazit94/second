package com.example.second.repos;

import com.example.second.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepo extends CrudRepository<Users, Integer> {

    List<Users> findByLogin(String id);

}
