package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.Map;

public interface VehicleService {

	Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException;
	
	Map<String, String> updateVehicleStats(String renavam, Long orderId, String status) throws IOException;
	
	Map<String, String> updateVehicleLogData(String renavam, Long orderId, String logData) throws IOException;
	
}
