package com.sgpd.br.bpm.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgpd.br.bpm.gateway.VehicleServiceImpl;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleService vehicleService;
	
	public Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException{
		return vehicleService.getVehicleByRenavam(renavam, orderId);
	}
}
