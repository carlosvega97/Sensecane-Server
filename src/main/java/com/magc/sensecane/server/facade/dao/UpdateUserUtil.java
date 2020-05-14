package com.magc.sensecane.server.facade.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.magc.sensecane.framework.container.Container;
import com.magc.sensecane.framework.dao.Dao;
import com.magc.sensecane.framework.generics.MonoParameterizedFunction;
import com.magc.sensecane.framework.model.database.TableEntity;
import com.magc.sensecane.server.facade.AbstractDaoUtil;
import com.magc.sensecane.server.facade.DaoFacade;
import com.magc.sensecane.server.model.User;
import com.magc.sensecane.server.model.database.CarerTable;
import com.magc.sensecane.server.model.database.DoctorTable;
import com.magc.sensecane.server.model.database.PatientTable;

public class UpdateUserUtil extends AbstractDaoUtil implements MonoParameterizedFunction<Map<String, String>, User> {

	public UpdateUserUtil(Container container) {
		super(container);
	}

	@Override
	public User apply(Map<String, String> params) {
		User result = null;
		User user = DaoFacade.find(Integer.parseInt(params.get("id")));
		switch (new GetUserTypeUtil<User>(container).apply(user)) {
			case CARER:
				result = asCarer(user, params);
				break;
			case PATIENT:
				result = asPatient(user, params);
				break;
			case DOCTOR:
				result = asDoctor(user, params);
				break;
		}
		return result;
	}

	private User asCarer(User user, Map<String, String> params) {
		return create(CarerTable.class, user.getId(), params.get("username"), params.get("password"), user.getDni(), 
				user.getToken(), params.get("firstName"), params.get("lastName"), user.getIp(), user.getUserAgent(), user.getLastLogin());
	}
	
	private User asPatient(User user, Map<String, String> params) {
		return create(PatientTable.class, user.getId(), params.get("username"), params.get("password"), user.getDni(), 
				user.getToken(), params.get("firstName"), params.get("lastName"), user.getIp(), user.getUserAgent(), user.getLastLogin());
	}
	
	private User asDoctor(User user, Map<String, String> params) {
		return create(DoctorTable.class, user.getId(), params.get("username"), params.get("password"), user.getDni(), 
				user.getToken(), params.get("firstName"), params.get("lastName"), user.getIp(), user.getUserAgent(), user.getLastLogin());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private <T extends TableEntity> User create(Class<T> clazz, Integer id, String username, String password, String dni, String token, String firstName, String lastName, String ip, String userAgent, Long lastLogin) {
		User result = null;
		try {
			Constructor<T> constructor = clazz.getConstructor(Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, Long.class);
			T instance = constructor.newInstance(id, username, password, dni, token, firstName, lastName, ip, userAgent, lastLogin);
			Dao<T> dao = get(clazz);
			result = (User) dao.insertOrUpdate(instance);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}






