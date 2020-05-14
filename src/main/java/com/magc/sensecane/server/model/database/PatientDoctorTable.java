package com.magc.sensecane.server.model.database;

import com.magc.sensecane.framework.model.database.TableEntity;
import com.magc.sensecane.framework.model.database.annotation.Autogenerated;
import com.magc.sensecane.framework.model.database.annotation.Column;
import com.magc.sensecane.framework.model.database.annotation.PrimaryKey;
import com.magc.sensecane.framework.model.database.annotation.Table;

@Table("patient_doctor")
public class PatientDoctorTable extends TableEntity<Integer> {

	@PrimaryKey @Column("id") @Autogenerated private final Integer id;
	@Column("patient") private final Integer patient;
	@Column("doctor") private final Integer doctor;
	
	public PatientDoctorTable() {
		this(null, null, null);
	}
	
	public PatientDoctorTable(Integer id, Integer patientId, Integer doctorId) {
		super();
		this.id = id;
		this.patient = patientId;
		this.doctor = doctorId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPatient() {
		return patient;
	}

	public Integer getDoctor() {
		return doctor;
	}
	
}
