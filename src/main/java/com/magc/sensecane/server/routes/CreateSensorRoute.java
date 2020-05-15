package com.magc.sensecane.server.routes;

import java.util.Map;

import com.magc.sensecane.framework.container.Container;
import com.magc.sensecane.framework.model.json.PreSerializedJson;
import com.magc.sensecane.framework.spark.AbstractPostRoute;
import com.magc.sensecane.server.facade.DaoFacade;
import com.magc.sensecane.server.model.User;
import com.magc.sensecane.server.model.database.PatientSensorTable;

import spark.Request;
import spark.Response;

public class CreateSensorRoute extends AbstractPostRoute<PatientSensorTable> {

	public CreateSensorRoute(Container container) {
		super(container);
	}

	@Override
	public PreSerializedJson<PatientSensorTable> serve(Request request, Response response) throws Exception {
		PatientSensorTable sensor = null;
		
		Map<String, String> params = super.getParams(request, "username", "password", "dni", "firstName", "lastName", "type");
//		result = new PreSerializedJson<User>(DaoFacade.createOrUpdateUser(params), "password", "token", "ip", "userAgent", "lastLogin");
		
		return new PreSerializedJson<PatientSensorTable>(sensor, "*");
	}

}
