package com.sgpd.br.bpm.delegate;

import java.util.Map;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgpd.br.bpm.gateway.VehicleServiceImpl;

@Component
public class RecoverVehicleDataDelegate implements JavaDelegate {
	
	@Autowired
	private VehicleServiceImpl vehicleService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String renavam = execution.getVariable("renavam").toString();
		Long orderId = Long.parseLong(execution.getVariable("orderId").toString());
		Map<String, String> responseVehicle = vehicleService.getVehicleByRenavam(renavam, orderId);
		if (responseVehicle != null) {
			execution.setVariable("placa", responseVehicle.get("placa"));
		}
	}

}
