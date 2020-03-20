package fr.laposte.sv.project.back.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WebService implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String webService;
    String libelleWebService;
    Date dateCreation;
@ManyToOne
        @JoinColumn(name = "code_application")
        @JsonIgnoreProperties
//    @JoinTable(name = "application_web_service",
//            joinColumns = @JoinColumn(name = "application_code_application"),
//            inverseJoinColumns = @JoinColumn(name = "web_service_id"))
    Application application;


}
