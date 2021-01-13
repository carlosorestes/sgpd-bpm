package com.sgpd.br.bpm.gateway;

import java.io.IOException;
import java.util.Map;

public interface DenatranService {
	
	Map<String, String> getVehicle(String renavam, String placa) throws IOException;

}
