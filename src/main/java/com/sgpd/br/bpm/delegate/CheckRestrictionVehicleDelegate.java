package com.sgpd.br.bpm.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckRestrictionVehicleDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ObjectMapper denatranObject = new ObjectMapper();
		Map<String, String> denatranMap = denatranObject.convertValue(execution.getVariable("denatranData"), Map.class);
		if(denatranMap != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			String[] restrictions = objectMapper.writeValueAsString(denatranMap.get("restrictions")).split(",");
			execution.setVariable("hasRestriction", hasRestriction(restrictions));
		}
	}

	private boolean hasRestriction(String[] restrictions) {
		for(String restriction: restrictions) {
			if(!restriction.equals("SEM RESTRICAO")) {
				return true;
			}
		}
		return false;
	}

}
