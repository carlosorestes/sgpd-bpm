package com.sgpd.br.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgpd.br.bpm.gateway.VehicleServiceImpl;

@Component
public class RegisterLogDataDelegate implements JavaDelegate {
	
	@Autowired
	private VehicleServiceImpl vehicleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String denatranData = execution.getVariable("denatranData").toString();
		String renavam = execution.getVariable("renavam").toString();
		Long orderId = Long.valueOf(execution.getVariable("orderId").toString());
		
		vehicleService.updateVehicleLogData(renavam, orderId, denatranData);
	}

}
