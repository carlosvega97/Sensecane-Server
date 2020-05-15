package com.magc.sensecane.server.routes;

import com.magc.sensecane.framework.container.Container;
import com.magc.sensecane.framework.model.json.PreSerializedJson;
import com.magc.sensecane.framework.spark.AbstractPutRoute;
import com.magc.sensecane.server.facade.DaoFacade;
import com.magc.sensecane.server.model.User;

import spark.Request;
import spark.Response;

public class UpdateUserRoute extends AbstractPutRoute<User> {

	public UpdateUserRoute(Container container) {
		super(container);
	}

	@Override
	public PreSerializedJson<User> serve(Request request, Response response) throws Exception {
		User user = DaoFacade.createOrUpdateUser(super.getParams(request, "id", "username", "password", "dni", "firstName", "lastName"));
		return new PreSerializedJson<User>(user, "id", "username", "dni", "firstName", "lastName");
	}
}
