package com.magc.sensecane.server.routes;

import java.util.Map;

import com.google.gson.GsonBuilder;
import com.magc.sensecane.framework.container.Container;
import com.magc.sensecane.framework.model.json.PreSerializedJson;
import com.magc.sensecane.framework.spark.AbstractPostRoute;
import com.magc.sensecane.server.facade.DaoFacade;
import com.magc.sensecane.server.model.User;
import com.magc.sensecane.server.model.database.UserTable;

import spark.Request;
import spark.Response;

public class CreateUserRoute extends AbstractPostRoute<String> {

	public CreateUserRoute(Container container) {
		super(container);
	}

	@Override
	public String handle(Request request, Response response) throws Exception {
		PreSerializedJson<User> result = null;
		
		try {
			if (super.isValidRequest(request, response)) {
				Map<String, String> params = super.getParams(request, "username", "password", "dni", "firstName", "lastName", "type");
				result = new PreSerializedJson<User>(DaoFacade.createOrUpdateUser(params), "password", "token", "ip", "userAgent", "lastLogin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return super.toJson(result);
	}
}
