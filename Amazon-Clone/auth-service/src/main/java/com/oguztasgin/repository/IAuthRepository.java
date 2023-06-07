package com.oguztasgin.repository;

import com.oguztasgin.repository.entity.Auth;
import com.oguztasgin.repository.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {


    Optional<Auth> findOptionalByUsernameAndPassword(String username,String password);
    List<Auth>  findAllByRole(ERole role);
    @Query("select COUNT(*)>0 from Auth a where a.username=?1")
    Boolean isUsername(String username);

}
