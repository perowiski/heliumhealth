package com.heliumhealth.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heliumhealth.models.IPAddress;
import com.heliumhealth.repository.IPAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class IPAddressCrawlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPAddressCrawlerService.class);

    private IPAddressRepository ipAddressRepository;
    @Value("${heliumhealth.gitUrl}")
    private String gitUrl;

    private final ObjectMapper objectMapper;

    @Autowired
    private IPAddressCrawlerService(IPAddressRepository ipAddressRepository, ObjectMapper objectMapper){
        this.ipAddressRepository = ipAddressRepository;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 10000000)
    public void cronJobSch() {
        List<String> ipAddressList = getIPAddressList();
        ipAddressList.forEach(s -> {
            Optional<IPAddress> optionalIPAddress = ipAddressRepository.findByAddress(s);
            if (optionalIPAddress.isEmpty()){
                IPAddress ipAddress = new IPAddress();
                ipAddress.setAddress(s);
                ipAddressRepository.save(ipAddress);
            }
        });
    }


    public List<String> getIPAddressList(){
        List<String> ipAddressList = new ArrayList<>();
        try {
            URL url = new URL(gitUrl);
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String i;
            while ((i = read.readLine()) != null){
                if(!i.contains("#")){
                    LOGGER.info("IPAddressCrawlerService getIPAddressList Added to DB: {}", objectMapper.writeValueAsString(i));
                    ipAddressList.add(i);
                }
            }
            read.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipAddressList;
    }
}
