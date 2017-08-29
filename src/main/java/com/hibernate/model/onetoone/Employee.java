package com.hibernate.model.onetoone;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//import javax.persistence.ForeignKey;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "CODE")
	private Long code; 
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "NAME")
	private String name; 
	
	@Column(name = "BIRTHDAY")
	private Date birthday; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_ADDRESS")
	private Address address;
	
	public Employee(){
		
	}

	public Employee(Long code, String surname, String name, Date birthday) {
		this.code = code;
		this.surname = surname;
		this.name = name;
		this.birthday = birthday;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [code=" + code + ", surname=" + surname + ", name=" + name + ", birthday=" + birthday
				+ ", address=" + address + "]";
	}
	
}
