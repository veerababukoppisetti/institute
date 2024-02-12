package com.being.institutemanagementsystem.features.data.model.persistence;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instituteregistration")
public class RegistrationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
      private  int id;
    /** Reference to the name. */

    @Column(name = "name", nullable = false, length = 30)
    private String name;
    /** Reference to the email. */

    @Column(name = "email", nullable = false, length = 20)
    private String email;
    /** Reference to the contact. */

    @Column(name = "contact", nullable = false, length = 10)
    private String contact;
    /** Reference to the location. */

    @Column(name = "location", nullable = false, length = 20)
    private String location;
    /** Reference to the courseName. */

    @Column(name = "coursename", nullable = false, length = 15)
    private String courseName;
    /** Reference to the instituteName. */
    @Column(name = "institutename", nullable = false, length = 15)
    private String instituteName;

}
