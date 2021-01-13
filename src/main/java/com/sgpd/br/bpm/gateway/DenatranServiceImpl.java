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
public class DenatranServiceImpl implements DenatranService {
	
	private final RestTemplate restTemplate;

	public DenatranServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Map<String, String> getVehicle(String renavam, String placa) throws IOException {
		String url = String.format("http://localhost:9292/veiculo?codigoRenavam=%s&placa=%s", renavam, placa);
		Map<String, String> responseMap = new HashMap<String, String>();
		Map<String, Object> restricaoMap = new HashMap<String, Object>();

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `accept` header
		headers.setContentType(MediaType.APPLICATION_JSON);

		// build the request
		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
		if (result.getStatusCode() == HttpStatus.OK && result.getBody().toString().length() > 3) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = mapper.readTree(result.getBody());
			StringBuilder restrictions = new StringBuilder();
			if(!actualObj.isEmpty()) {
				restrictions.append(actualObj.get(0).get("restricao1").get("descricao").textValue()+",")
							.append(actualObj.get(0).get("restricao2").get("descricao").textValue()+",")
							.append(actualObj.get(0).get("restricao3").get("descricao").textValue()+",")
							.append(actualObj.get(0).get("restricao4").get("descricao").textValue());
				
				responseMap.put("restrictions", restrictions.toString());
			}
		}
		return responseMap;
	}

}
