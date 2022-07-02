package com.essam.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.essam.springdemo.entity.Customer;
import com.essam.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create a query .... sorted by the last name
		Query<Customer> customerQuery = session.createQuery("from Customer order by firstName", Customer.class);
		
		// execute the query and get the result list
		List<Customer> customers = customerQuery.getResultList();
		
		// return the result list
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, customerId);
		return customer;
	}

	@Override
	public void deleteCustomer(int customerId) {
		Session session = sessionFactory.getCurrentSession();
		Query deleteQuery = session.createQuery("delete from Customer where id=:customerId");
		deleteQuery.setParameter("customerId", customerId);
		deleteQuery.executeUpdate();
		
		
	}

	@Override
	public List<Customer> searchCustomers(String searchName) {
		Session session = sessionFactory.getCurrentSession();
		
		Query<Customer> customersQuery = null;
		
		if (searchName != null && searchName.trim().length() > 0) {
			customersQuery = session.createQuery("from Customer where "
					+ "lower(firstName) like :theSearchName or lower(lastName) like :theSearchName", Customer.class);
			
			customersQuery.setParameter("theSearchName", "%" + searchName.toLowerCase() + "%");
		} else {
			 customersQuery =session.createQuery("from Customer", Customer.class);       
		}
		
		
		
		List<Customer> customers = customersQuery.getResultList();
		
		return customers;
	}

	@Override
	public List<Customer> getCustomers(int sortField) {
		Session session = sessionFactory.getCurrentSession();
		
		String theFieldName = null;
		
		switch (sortField) {
			case SortUtils.FIRST_NAME: 
				theFieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				theFieldName = "lastName";
		}
		
		
		// create a query  
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> query = session.createQuery(queryString, Customer.class);
		return query.getResultList();
	}

}
















