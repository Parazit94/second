package com.example.second.repos;

import com.example.second.domain.Logo;
import org.springframework.data.repository.CrudRepository;
import sun.rmi.runtime.Log;

public interface LogoRepo extends CrudRepository<Logo,Integer> {
}
