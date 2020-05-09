package com.magc.sensecane.server.routes;

import java.util.Map;

import com.google.gson.GsonBuilder;
import com.magc.sensecane.framework.container.Container;
import com.magc.sensecane.framework.conversor.ConversorContainer;
import com.magc.sensecane.framework.dao.Dao;
import com.magc.sensecane.framework.dao.DaoContainer;
import com.magc.sensecane.model.database.UserTable;
import com.magc.sensecane.model.domain.User;

import spark.Request;
import spark.Response;

public class UpdateUserRoute extends AbstractPutRoute {

	public UpdateUserRoute(Container container) {
		super(container);
	}

	@Override
	public String handle(Request request, Response response) throws Exception {
		User user = null;
		
		Map<String, String> p = super.getParams(request, "id", "username", "password");
		System.out.println(p);
		try {
			if (p.containsKey("id") && p.containsKey("username") && p.containsKey("password")) {
				
				DaoContainer daocontainer = container.get(DaoContainer.class);
				ConversorContainer conversor = container.get(ConversorContainer.class);
				Dao<UserTable> udao = daocontainer.get(UserTable.class);
				
				UserTable ut = udao.find(Integer.valueOf(p.get("id")));
				UserTable usertable = udao.insertOrUpdate(new UserTable(ut.getId(), p.get("username"), p.get("password")));
				user = conversor.convert(usertable);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(user);
	}
}
