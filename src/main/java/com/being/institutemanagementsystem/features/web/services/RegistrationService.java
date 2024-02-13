package com.being.institutemanagementsystem.features.web.services;

import com.being.institutemanagementsystem.features.data.model.experience.student.CreateRegistrationRequest;
import com.being.institutemanagementsystem.features.data.model.experience.student.Registration;
import com.being.institutemanagementsystem.features.data.model.experience.student.UpdateRegistrationRequest;
import com.being.institutemanagementsystem.features.data.model.persistence.RegistrationEntity;
import com.being.institutemanagementsystem.features.data.repository.RegistrationRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }


    @Transactional
    public Registration createRegistration(CreateRegistrationRequest payload) {
        //1.transform payload to orm entity
        final RegistrationEntity student = RegistrationEntity.builder().name(payload.getName())
                .email(payload.getEmail())
                .contact(payload.getContact())
                .courseName(payload.getCourseName())
                .instituteName(payload.getInstituteName())
                .location(payload.getLocation()).build();
        //save the product
        final RegistrationEntity newStudent = registrationRepository.save(student);
        return Registration.builder().id(newStudent.getId())
                .name(newStudent.getName())
                .email(newStudent.getEmail())
                .contact(newStudent.getContact())
                .courseName(newStudent.getCourseName())
                .instituteName(newStudent.getInstituteName())
                .location(newStudent.getLocation()).build();


    }

    @Transactional
    public Registration updateRegistration(Integer instituteId, UpdateRegistrationRequest payload) {

        // 1. Validate the dependencies.
        final RegistrationEntity matchingStdId = registrationRepository.findByInstituteId(instituteId);
            if (matchingStdId == null) {
                throw new ServiceException("Unable to find institute with id " + instituteId);
            }
            // 2. Transform the experience model to a persistence model and delegate to the save(..)
            final RegistrationEntity student = RegistrationEntity.builder().id(payload.getId())
                    .name(payload.getName())
                    .email(payload.getEmail())
                    .contact(payload.getContact())
                    .courseName(payload.getCourseName())
                    .instituteName(payload.getInstituteName())
                    .location(payload.getLocation()).build();

            //save the product

            final RegistrationEntity updateInstance = registrationRepository.save(student);
            return Registration.builder().id(updateInstance.getId())
                    .name(updateInstance.getName())
                    .email(updateInstance.getEmail())
                    .contact(updateInstance.getContact())
                    .courseName(updateInstance.getCourseName())
                    .instituteName(updateInstance.getInstituteName())
                    .location(updateInstance.getLocation()).build();
        }


    public Registration findByInstitute(Integer instituteId) {
        // 1. Validate the dependencies.
        final RegistrationEntity matchingInstance = registrationRepository.findByInstituteId(instituteId);
        //.orElseThrow(() -> ResourceNotFoundException.instance(Errors.RESOURCE_NOT_FOUND,instituteId));
        if (matchingInstance == null) {
                throw new ServiceException("Unable to find institute with id " + instituteId);
            }
            // 2. Transform the matching entity to the desired output.

            return Registration.builder().id(matchingInstance.getId())
                    .name(matchingInstance.getName())
                    .email(matchingInstance.getEmail())
                    .contact(matchingInstance.getContact())
                    .courseName(matchingInstance.getCourseName())
                    .instituteName(matchingInstance.getInstituteName())
                    .location(matchingInstance.getLocation()).build();


    }}