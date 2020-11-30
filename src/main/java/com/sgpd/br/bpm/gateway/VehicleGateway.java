package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class VehicleGateway {
	
	private final RestTemplate restTemplate;

	public VehicleGateway(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	 
	 public Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException {
		 String url = String.format("http://localhost:8080/vehicle/renavam/%s/order/%x",renavam, orderId);
			Map<String, String> responseMap = new HashMap<String, String>();
			
			 // create headers
		    HttpHeaders headers = new HttpHeaders();
		    // set `accept` header
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    // build the request
		    HttpEntity request = new HttpEntity(headers);
			
			ResponseEntity<String> response = this.restTemplate.postForEntity(url, request, String.class);
		    if(response.getStatusCode() == HttpStatus.OK) {
		    	ObjectMapper mapper = new ObjectMapper();
		    	JsonNode actualObj = mapper.readTree(response.getBody());
		    	responseMap.put("placa", actualObj.get("placa").textValue());
		    	responseMap.put("renavam", actualObj.get("renavam").textValue());
		    } 

		    return responseMap;
	 }

}
