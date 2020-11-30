package com.sgpd.br.bpm.delegate;

import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ReceivedRequestDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String renavam = execution.getVariable("renavam").toString();
		Long orderId = Long.parseLong(execution.getVariable("orderId").toString());
		
		if(!StringUtils.isEmpty(renavam) && orderId > 0) {
			execution.setVariable("renavam", renavam);
			execution.setVariable("orderId", orderId);
		}
	}
}
