package com.heliumhealth.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heliumhealth.dtos.RequestDTO;
import com.heliumhealth.dtos.ResponseDTO;
import com.heliumhealth.services.IPAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "heliumhealth/api")
@RestController
public class IPVerifierController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPVerifierController.class);

    private IPAddressService ipAddressService;

    private ObjectMapper objectMapper;

    @Autowired
    private IPVerifierController(IPAddressService ipAddressService, ObjectMapper objectMapper){
        this.ipAddressService = ipAddressService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/verifyIPAddress")
    public ResponseDTO verifyIPAddress(@Valid @RequestBody RequestDTO requestDTO) throws JsonProcessingException {
        LOGGER.info("IPVerifierController verifyIPAddress request: {}", objectMapper.writeValueAsString(requestDTO));
        ResponseDTO responseDTO =  ipAddressService.verifyIPAddress(requestDTO);
        LOGGER.info("IPVerifierController verifyIPAddress response: {}", objectMapper.writeValueAsString(responseDTO));
        return responseDTO;
    }
}
