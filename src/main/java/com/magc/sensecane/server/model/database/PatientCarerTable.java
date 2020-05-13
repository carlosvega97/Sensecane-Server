package com.magc.sensecane.server.model.database;

import com.magc.sensecane.framework.model.database.TableEntity;
import com.magc.sensecane.framework.model.database.annotation.Autogenerated;
import com.magc.sensecane.framework.model.database.annotation.Column;
import com.magc.sensecane.framework.model.database.annotation.PrimaryKey;
import com.magc.sensecane.framework.model.database.annotation.Table;

@Table(name = "patient_carer")
public class PatientCarerTable extends TableEntity<Integer> {

	@PrimaryKey @Column(name = "id") @Autogenerated private final Integer id;
	@Column(name = "patient") private final Integer patientId;
	@Column(name = "carer") private final Integer carerId;
	
	public PatientCarerTable() {
		this(null, null, null);
	}
	
	public PatientCarerTable(Integer id, Integer patientId, Integer carerId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.carerId = carerId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public Integer getCarerId() {
		return carerId;
	}
	
}