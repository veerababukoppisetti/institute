package com.being.institutemanagementsystem;

import com.being.institutemanagementsystem.features.data.model.persistence.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Integer> {
//@Query(value = "SELECT * FROM public.instituteregistration where id = :id",nativeQuery = true)
//    RegistrationEntity findByInstituteId(Integer id);
}
