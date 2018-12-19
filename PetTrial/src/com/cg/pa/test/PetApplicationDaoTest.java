package com.cg.pa.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import com.cg.pa.bean.PetBean;
import com.cg.pa.dao.PetDaoImpl;
import com.cg.pa.exception.PetException;

public class PetApplicationDaoTest
{

	static PetBean pet;
	static PetDaoImpl dao;
	
	@BeforeClass
	public static void init()
	{
		System.out.println("Before Class");
		dao=new PetDaoImpl();
		pet=new PetBean();
	}
	
	//@Ignore
	@Test
	public void addOwnerDetailsTest() throws PetException, ClassNotFoundException, SQLException, IOException
	{
		pet.setOwnerName("Rihaan");
		pet.setOwnerPhNo("9824756325");
		pet.setOwnerAge("25");
		pet.setVaccineDate("22/10/2018");
		pet.setOwnerId("10021");
		assertTrue("Data inserted successfully", Integer.parseInt(dao.addPetOwnerDetails(pet))>10004);
	
	}
	@Test
	public void viewAllTest() throws ClassNotFoundException, PetException, IOException, SQLException
	{
		assertNotNull(dao.viewPetOwnerDetails("10004"));
	}
	@Test
	public void retrieveAllTest() throws PetException, ClassNotFoundException, SQLException, IOException
	{
		assertNotNull(dao.retriveAll());
	}
	
	@Test
	public void removeDetailsTest() throws ClassNotFoundException, PetException, IOException, SQLException
	{
	{

		assertNull(dao.viewRemoveOwnerDetails("10025"));
	}

//	@AfterAll
//	void dummy()
//	{
//		System.out.println("Record deleted successfully");
//	}
//	
	
	
//	@Test
//	public void testById1() throws PetException, ClassNotFoundException, IOException, SQLException 
//	{
//		assertEquals("TestDate", dao.viewPetOwnerDetails("10003").getOwnerName());
//	}
}
}
