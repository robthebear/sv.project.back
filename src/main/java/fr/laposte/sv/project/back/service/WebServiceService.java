package fr.laposte.sv.project.back.service;

import fr.laposte.sv.project.back.model.WebService;
import org.springframework.stereotype.Service;

@Service
public interface WebServiceService {

    WebService saveWebService(WebService webService);
}
