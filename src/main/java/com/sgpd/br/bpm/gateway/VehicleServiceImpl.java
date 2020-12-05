package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final RestTemplate restTemplate;

	public VehicleServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException {
		String url = String.format("http://localhost:8080/vehicle/renavam/%s/order/%x", renavam, orderId);
		Map<String, String> responseMap = new HashMap<String, String>();

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic amF2YWludXNlOnBhc3N3b3Jk");

		// build the request
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		if (result.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(result.getBody());
			responseMap.put("placa", actualObj.get("placa").textValue());
			responseMap.put("renavam", actualObj.get("renavam").textValue());
		}

		return responseMap;
	}

}
