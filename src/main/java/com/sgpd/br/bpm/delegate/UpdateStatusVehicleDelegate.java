package com.sgpd.br.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sgpd.br.bpm.gateway.VehicleServiceImpl;

@Component
public class UpdateStatusVehicleDelegate implements JavaDelegate {
	
	@Autowired
	private VehicleServiceImpl vehicleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String statusUpdate = "VEICULO NAO ENCONTRADO";
		String renavam = execution.getVariable("renavam").toString();
		Long orderId = Long.valueOf(execution.getVariable("orderId").toString());
		Boolean vehicleHasFound = Boolean.parseBoolean(execution.getVariable("vehicleHasFound").toString());
		
		if(vehicleHasFound) {
			statusUpdate = Boolean.parseBoolean(execution.getVariable("hasRestriction").toString())?
					"VEICULO COM RESTRICAO":"VEICULO SEM RESTRICAO";
		}
		vehicleService.updateVehicleStats(renavam, orderId, statusUpdate);
	}

}
