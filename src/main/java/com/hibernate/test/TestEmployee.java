package com.hibernate.test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hibernate.model.onetoone.Address;
import com.hibernate.model.onetoone.Employee;

public class TestEmployee {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("application");
	
	public static void main(String[] args) throws InterruptedException {
		
		Employee e1 = new Employee(createRandomLong(), "Garay", "Nicolas Matias", new GregorianCalendar(1989,22,11).getTime());
		Employee e2 = new Employee(createRandomLong(), "Garay", "Martin Lucas", new GregorianCalendar(1993,15,3).getTime());
		
		e1.setAddress(new Address(createRandomLong(), "Pellegrini 1217", "Rosario", "Santa Fe", "Argentina"));
		e2.setAddress(new Address(createRandomLong(), "Pellegrini 1217", "Rosario", "Santa Fe", "Argentina"));
		
		// Deleting previous rows
		deteleAllEmployee();
		
		printAll();
		
		EntityManager manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(e1);
		manager.persist(e2);
		manager.getTransaction().commit();
		
		// Se ve que con estos ejemplos quedaba cacheada la entidad y en Address tenia valor nulo
		// Otra solución es hacer que los objetos sean detached una vez que terminamos de utilizarlos. 
		manager.close();
		
		printAll();
		
		manager = emf.createEntityManager();
		
		manager.getTransaction().begin();
		e1 = manager.merge(e1);
		manager.remove(e1);
		manager.getTransaction().commit();
		
		manager.close();
		
		printAll();
	}
	
	private static void printAll() {
		
		EntityManager manager = emf.createEntityManager();
		
		List<Employee> employees = manager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        
		System.out.println("In this database there are " + employees.size() + " employees");
		
		for(Employee e : employees) {
			// Se ve que con estos ejemplos quedaba cacheada la entidad y en Address tenia valor nulo, al hacer el refresh se vuelve a cargar dicho valor desde la BBDD
			//manager.refresh(e);
			System.out.println(e.toString());
		}
		
		manager.close();
	}
	
	private static void deteleAllEmployee() {
		
		EntityManager manager = emf.createEntityManager();
		
		List<Employee> employees = manager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
        
		manager.getTransaction().begin();
		for(Employee e : employees) {
			manager.remove(e);
		}
		manager.getTransaction().commit();
	}
	
	private static Long createRandomLong() {
		long leftLimit = 1L;
	    long rightLimit = 100000L;
	    return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}
}
