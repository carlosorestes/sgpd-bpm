package com.sgpd.br.bpm.delegate;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgpd.br.bpm.gateway.DenatranServiceImpl;

@Component
public class GetDataVehicleAPIDelegate implements JavaDelegate {
	
	@Autowired
	private DenatranServiceImpl denatranService; 
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String renavam = execution.getVariable("renavam").toString();
		String placa = execution.getVariable("placa").toString();
		Boolean vehicleHasFound = false;
		Map<String, Object> denatranData = new HashMap<String, Object>();
		Map<String, String> responseVehicle = denatranService.getVehicle(renavam, placa);
		if (!responseVehicle.isEmpty() && !responseVehicle.get("restrictions").isEmpty()) {
			denatranData.put("restrictions", responseVehicle.get("restrictions"));
			execution.setVariable("denatranData", denatranData);
			vehicleHasFound = true;
		}
		execution.setVariable("vehicleHasFound", vehicleHasFound);
	}
}
