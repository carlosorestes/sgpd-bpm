package com.sgpd.br.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class GetDataVehicleAPIDelegate implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
	}
}
