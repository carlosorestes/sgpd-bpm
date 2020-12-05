package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.Map;

public interface VehicleService {

	Map<String, String> getVehicleByRenavam(String renavam, Long orderId) throws IOException;

}
