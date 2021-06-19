package com.heliumhealth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.heliumhealth.dtos.RequestDTO;
import com.heliumhealth.dtos.ResponseDTO;
import com.heliumhealth.models.IPAddress;
import com.heliumhealth.repository.IPAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IPAddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPAddressService.class);

    private IPAddressRepository ipAddressRepository;

    @Autowired
    private IPAddressService(IPAddressRepository ipAddressRepository){
        this.ipAddressRepository = ipAddressRepository;
    }

    public ResponseDTO verifyIPAddress(RequestDTO requestDTO) throws JsonProcessingException {
        Optional<IPAddress> optionalIPAddress = ipAddressRepository.findByAddress(requestDTO.getIPAddress());
        if (optionalIPAddress.isEmpty()){
            LOGGER.info("IPAddressService verifyIPAddress message: {}", "IPAddress not in blocked list");
            throw new NoSuchElementException("IPAddress not in blocked list");
        }else {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setIPAddress(optionalIPAddress.get().getAddress());
            return responseDTO;
        }
    }
}
