package fr.laposte.sv.project.back.service.impl;

import fr.laposte.sv.project.back.model.Correspondant;
import fr.laposte.sv.project.back.repository.CorrespondantRepository;
import fr.laposte.sv.project.back.service.CorrespondantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CorrespondantServiceImpl implements CorrespondantService {


    @Autowired
    CorrespondantRepository correspondantRepository;

//    @Override
//    public Correspondant deleteApplication(String idCorrespondant, String idApplication) {
//        String  relation = correspondantRepository.getOne(idCorrespondant).getApplications(idApplication);
//    }


}
