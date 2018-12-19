package com.cg.pa.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;
import com.cg.pa.util.*;
import com.cg.pa.bean.PetBean;
import com.cg.pa.exception.PetException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class PetDaoImpl implements IPetDAO
{
	Logger logger=Logger.getRootLogger();
	public PetDaoImpl()
	{
		PropertyConfigurator.configure("resources//log4j.properties");
	}

		/*******************************************************************************************************
		 - Function Name	:	addPetOwnerDetails(PetBean db)
		 - Input Parameters	:	PetBean db
		 - Return Type		:	String
		 - Author			:	Sohan Madolli
		 - Creation Date	:	15/12/2018
		 - Description		:	Adding Pet Owner Details
		 ********************************************************************************************************/

	//@Override
	public String addPetOwnerDetails(PetBean db) throws PetException, SQLException, ClassNotFoundException, IOException 
	{
		Connection con= DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		Statement st=con.createStatement();
		
		String ownerId=null;
		int queryResult=0;
		
		try
		{
			
			preparedStatement=con.prepareStatement(QueryMapper.Insert_Query);
			
			preparedStatement.setString(1, db.getOwnerName());
			preparedStatement.setString(2, db.getOwnerPhNo());
			preparedStatement.setString(3, db.getOwnerAge());
			preparedStatement.setString(4, db.getVaccineDate());
			//preparedStatement.setString(5,db.getVaccineDate());
			
			preparedStatement.executeUpdate();
			
			
			resultSet=st.executeQuery(QueryMapper.Retrieve_all);
			while(resultSet.next())
			{
				ownerId = resultSet.getString(1);
				System.out.println(" Owner ID: "+resultSet.getString(1)+" Owner Name: "+resultSet.getString(2)+" Ph-no: "+resultSet.getString(3)+" Owner Age: "+resultSet.getString(4)+" Vaccine Date is: "+resultSet.getString(5));
			}
			return ownerId;
		}
		catch(SQLException sql)
		{
			logger.error(sql.getMessage());
			System.out.println("error:"+sql.getMessage());
		}
		finally
		{
			try 
			{
				//resultSet.close();
				preparedStatement.close();
				con.close();
			}
			catch (SQLException sqlException) 
			{
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage());
				throw new PetException("Error in closing db connection");

			}
		}
		return null;
		
	}

			/*******************************************************************************************************
			 - Function Name	:	viewPetOwnerDetails(String petOwnerId)
			 - Input Parameters	:	String petOwnerId
			 - Return Type		:	PetBean
			 - Author			:	Sohan Madolli
			 - Creation Date	:	15/12/2018
			 - Description		:	Viewing Pet Owner Details
			 ********************************************************************************************************/

	//@Override
	public PetBean viewPetOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		PetBean Bean=new PetBean();
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		
		try
		{
		preparedStatement=con.prepareStatement(QueryMapper.View_Details);
		preparedStatement.setString(1, petOwnerId);
		resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			Bean.setOwnerId(resultSet.getString(1));
			Bean.setOwnerName(resultSet.getString(2));
			Bean.setOwnerPhNo(resultSet.getString(3));
			Bean.setOwnerAge(resultSet.getString(4));
			Bean.setVaccineDate(resultSet.getString(5));
		}
		if( Bean != null)
		{
			logger.info("Record Found Successfully");
			return Bean;
		}
		else
		{
			logger.info("Record Not Found Successfully");
			return null;
		}
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
			throw new PetException(e.getMessage());
		}
		finally
		{
			try 
			{
				resultSet.close();
				preparedStatement.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new PetException("Error in closing db connection");

			}
		}
		
	

	
}
			/*******************************************************************************************************
			 - Function Name	:	retriveAll()
			 - Input Parameters	:	
			 - Return Type		:	List
			 - Author			:	Sohan Madolli
			 - Creation Date	:	15/12/2018
			 - Description		:	Retrieving all Pet Owner Details
			 ********************************************************************************************************/
	
	//@Override
	public List<PetBean> retriveAll() throws PetException, SQLException, IOException, ClassNotFoundException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		//DonorBean Bean=new DonorBean();
		List<PetBean> list=null;
		ResultSet rs=null;
		try {
		rs=st.executeQuery(QueryMapper.Retrieve_all);
		list=new ArrayList<>();
		while(rs.next())
		{
			PetBean Bean=new PetBean();
			Bean.setOwnerId(rs.getString(1));
			Bean.setOwnerName(rs.getString(2));
			Bean.setOwnerPhNo(rs.getString(3));
			Bean.setOwnerAge(rs.getString(4));
			Bean.setVaccineDate(rs.getString(5));
			
			//donorId=rs.getString(1);
			list.add(Bean);
		}
	}
		catch(SQLException e)
		{
			logger.error(e.getMessage());
			throw new PetException("Error in closing db connection");

		}
		finally
		{
			try 
			{
				rs.close();
				st.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new PetException("Error in closing db connection");

			}
		}
		return list;
	}
	
	

			/*******************************************************************************************************
			 - Function Name	:	viewRemoveOwnerDetails(String petOwnerId)
			 - Input Parameters	:	String petOwnerId
			 - Return Type		:	PetBean
			 - Author			:	Sohan Madolli
			 - Creation Date	:	15/12/2018
			 - Description		:	Removing a single Pet Owner Record
			 ********************************************************************************************************/
	
	//@Override
	public PetBean viewRemoveOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		PetBean Bean=new PetBean();
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		
		
		try {
		//resultSet=st.executeQuery("delete from pet_owner_details where owner_Id='"+petOwnerId+"'" );
		preparedStatement=con.prepareStatement(QueryMapper.Delete_Details);
		preparedStatement.setString(1, petOwnerId);
		resultSet=preparedStatement.executeQuery();
//		while(resultSet.next())
//		{
//		Bean.setOwnerId(resultSet.getString(1));
//		Bean.setOwnerName(resultSet.getString(2));
//		Bean.setOwnerPhNo(resultSet.getString(3));
//		Bean.setOwnerAge(resultSet.getString(4));
//		Bean.setVaccineDate(resultSet.getString(5));
//		}
		//return Bean;
		}
		catch(SQLException e) 
		{
			logger.error(e.getMessage());
			throw new PetException("Error in closing db connection");

		}
		finally
		{
			try 
			{
				resultSet.close();
				preparedStatement.close();
				con.close();
			} 
			catch (SQLException e) 
			{
				logger.error(e.getMessage());
				throw new PetException("Error in closing db connection");

			}
		}
		return null;
	}
	

			/*******************************************************************************************************
			 - Function Name	:	knowVaccinationDate()
			 - Input Parameters	:	
			 - Return Type		:	void
			 - Author			:	Sohan Madolli
			 - Creation Date	:	15/12/2018
			 - Description		:	Next vaccination Date
			 ********************************************************************************************************/
	
	//@Override
	public void knowVaccinationDate() throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		Statement st=con.createStatement();
		
		String ownerId=null;
		int queryResult=0;
		try {


			String dummy;
			String ownerid=null;
			resultSet=st.executeQuery(QueryMapper.Retrieve_all);
			while(resultSet.next())
			{
				
				dummy = changeDate(resultSet.getString(5));
				ownerid= resultSet.getString(1);
				preparedStatement=con.prepareStatement(QueryMapper.Update_vaccine_date);
				preparedStatement.setString(2,ownerid);
				preparedStatement.setString(1,dummy);
				preparedStatement.executeUpdate();
				//System.out.println(" Owner ID: "+resultSet.getString(1)+" Owner Name: "+resultSet.getString(2)+" Ph-no: "+resultSet.getString(3)+" Owner Age: "+resultSet.getString(4)+" Vaccine Date is: "+resultSet.getString(5)+" Next Vaccination Date is: "+resultSet.getString(dummy));
				System.out.println("Next Vaccination Date of Pet whose Owner_id is "+resultSet.getString(1)+" scheduled at "+resultSet.getString(6));
			}
			
		}
		catch (Exception e) 
		{
			logger.error(e.getMessage());
			//throw new PetException("Error in closing db connection");
		}
	}
	
	
			/*******************************************************************************************************
			 - Function Name	:	changeDate(String date)
			 - Input Parameters	:	String date
			 - Return Type		:	String
			 - Author			:	Sohan Madolli
			 - Creation Date	:	15/12/2018
			 - Description		:	Change type of date
			 ********************************************************************************************************/
	
	private String changeDate(String date) {
		String changedDate=null;
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld=LocalDate.parse(date,formatter);
		LocalDate td =ld.plusYears(2);
		
		changedDate=td.toString();
		
		
		return changedDate;
	}

}
