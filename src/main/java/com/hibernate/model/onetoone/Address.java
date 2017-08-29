package com.hibernate.model.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;

	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private Employee employee;
	
	public Address() {}

	public Address(Long id, String address, String city, String state, String country) {
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", country="
				+ country + ", employee = "+ employee.getCode() +"]";
	}
}
