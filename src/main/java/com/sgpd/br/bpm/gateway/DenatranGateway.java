package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DenatranGateway {

	@Autowired
	private RestTemplate restTemplate;
	
	public Map<String, String> getInformationVehicle(String renavam, String placa) throws IOException {
		String url = String.format("http://localhost:9292/veiculo?codigoRenavam=%s&placa=%s",renavam, placa);
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
	    	responseMap.put("restricao1", actualObj.get("restricao1.codigo").textValue());
	    	responseMap.put("restricao2", actualObj.get("restricao2.codigo").textValue());
	    	responseMap.put("restricao3", actualObj.get("restricao3.codigo").textValue());
	    	responseMap.put("restricao4", actualObj.get("restricao4.codigo").textValue());
	    } 

	    return responseMap;
	}

}
