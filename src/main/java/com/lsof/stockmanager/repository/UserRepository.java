package com.lsof.stockmanager.repository;

import com.lsof.stockmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    // select * from user where = 'sof'
    List<User> findAllByFirstname(String firstname);

    //select * from user where firstname like '%Sof%'
    List<User> findAllByFirstnameContaining(String firstname);

    //select * from user where firstname ilike 'sof'
    List<User> findAllByFirstnameContainingIgnoreCase(String firstname);

    // select * from user u inner join account a in u.id = a.id_user and a.iban = 'AF12345678'
    List<User> findAllByAccount_Iban(String iban);

    // select * from user where firstname = 'sof%' and u.email = 'email@factory.com'
    User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    // select * from user where = 'Sof'
    @Query("from User where firstname = :fn")
    List<User> searchByFirstname(@Param(":fn") String firstname);

    //select * from user where firstname like '%Sof%'
    @Query("from User where firstname = '%:firstname%'")
    List<User> searchByFirstnameContaining(String firsname);

    // select * from user u inner join account a in u.id = a.id_user and a.iban = 'AF12345678'
    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIban(String iban);


}
