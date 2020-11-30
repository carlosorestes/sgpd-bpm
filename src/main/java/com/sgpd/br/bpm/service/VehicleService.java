package com.sgpd.br.bpm.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgpd.br.bpm.gateway.VehicleGateway;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleGateway vehicleGateway;
	
	public Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException{
		return vehicleGateway.getVehicleByRenavam(renavam, orderId);
	}
}
