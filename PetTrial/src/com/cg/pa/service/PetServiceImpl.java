package com.cg.pa.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.cg.pa.bean.PetBean;
import com.cg.pa.dao.IPetDAO;
import com.cg.pa.dao.PetDaoImpl;
import com.cg.pa.exception.PetException;

public class PetServiceImpl implements IPetService
{
	IPetDAO ipetdao=new PetDaoImpl();
	
	/*******************************************************************************************************
	 - Function Name	:	addPetOwnerDetails
	 - Input Parameters	:	PetBean object
	 - Return Type		:	String id
	 - Throws			:  	PetException
	 - Author			:	Sohan Madolli
	 - Creation Date	:	15/12/2018
	 - Description		:	adding pet to database calls dao method addPetOwnerDetails(pet)
	 ********************************************************************************************************/
	
	@Override
	public String addPetOwnerDetails(PetBean pet) throws PetException, ClassNotFoundException, SQLException, IOException 
	{
		
		String petSeq;
		petSeq=ipetdao.addPetOwnerDetails(pet);
		return petSeq;
	}
	
	
	/*******************************************************************************************************
	 - Function Name	: validateDetails(PetBean pet)
	 - Input Parameters	: PetBean pet
	 - Return Type		: void
	 - Throws		    : DonorException
	 - Author	      	: Sohan Madolli
	 - Creation Date	: 15/12/2018
	 - Description		: validates the PetBean object
	 ********************************************************************************************************/
	
	public void validateDetails(PetBean pet) throws PetException 
	{
		List<String> validationErrors = new ArrayList<String>();
		//validationErrors=null;
		if(!(isValidOwnerName(pet.getOwnerName())))
		{
			validationErrors.add("\n Owner Name should be in alphabets and must be 3 characters long!!!");
			//System.out.println(pet.getOwnerName());
		}
		if(!(isValidOwnerAge(pet.getOwnerAge())))
		{
			validationErrors.add("\n Owner Age should be in numerals and not more than 3 characters and less than 100!!!");
		}
		if(!(isValidOwnerPhNo(pet.getOwnerPhNo())))
		{
			validationErrors.add("\n Phone number should be 10 digits!!!");
		}
		if(!(isValidVaccineDate(pet.getVaccineDate())))
		{
			validationErrors.add("\n Date should be in the DD/MM/YYYY format!!!");
		}
		
		
		if(!validationErrors.isEmpty())
		{
			throw new PetException(validationErrors +"");
		}
		
		
	}

/*------------------------------------------------------------------
// Pet Owner Age Validation	
------------------------------------------------------------------*/	

	private boolean isValidOwnerAge(String ownerAge) 
	{
		Pattern age=Pattern.compile("^[1-9][0-9]$");
		Matcher ageMatcher=age.matcher(ownerAge);		
		return ageMatcher.matches();
		
	}

/*------------------------------------------------------------------
// Pet Owner Contact Number Validation 	
------------------------------------------------------------------*/	

	private boolean isValidOwnerPhNo(String ownerPhNo) 
	{
		Pattern Phno=Pattern.compile("^[6-9][0-9]{9}$");
		Matcher ownerPhNoMatcher=Phno.matcher(ownerPhNo);		
		return ownerPhNoMatcher.matches();
	}

/*------------------------------------------------------------------
//	Validation of Last Vaccination Date in DD/MM/YYYY format
------------------------------------------------------------------*/	
	
	
	private boolean isValidVaccineDate(String vaccineDate) 
	{
		Pattern date=Pattern.compile("^[0-3][0-9]/(0[1-9]|1[012])/[2][0][0-1][0-9]$");
		Matcher dateMatcher=date.matcher(vaccineDate);		
		return dateMatcher.matches();
		
	}
	
/*-----------------------------------------------------------------
// Validation of Pet Owner Name	
------------------------------------------------------------------*/	


	private boolean isValidOwnerName(String ownerName) 
	{
		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher=namePattern.matcher(ownerName);
		return nameMatcher.matches();
	}

	
	/*******************************************************************************************************
	 - Function Name	:	retriveAll()
	 - Input Parameters	:	
	 - Return Type		:	List
	 - Throws			:  	PetException
	 - Author			:	Sohan Madolli
	 - Creation Date	:	15/12/2018
	 - Description		:	calls dao method retriveAll()
	 ********************************************************************************************************/

	@Override
	public List<PetBean> retriveAll() throws PetException, ClassNotFoundException, SQLException, IOException 
	{
		List<PetBean> list = new ArrayList<>();
		list=ipetdao.retriveAll();
		return list;
	
	}
	/*******************************************************************************************************
	 - Function Name	:	viewPetOwnerDetails
	 - Input Parameters	:	String petOwnerId
	 - Return Type		:	PetBean object
	 - Throws		    :  	DonorException
	 - Author		    :	CAPGEMINI
	 - Creation Date	:	18/11/2016
	 - Description		:	calls dao method viewPetOwnerDetails(petOwnerId)
	 ********************************************************************************************************/
	
	
	@Override
	public PetBean viewPetOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException
	{
		PetBean bean;
		bean=ipetdao.viewPetOwnerDetails(petOwnerId);
		return bean;
		
	}

	public boolean validateOwnerId(String sid) 
	{
		
		Pattern namePattern=Pattern.compile("^[1][0][0][0-5][0-9]$");
		Matcher nameMatcher=namePattern.matcher(sid);
		if (nameMatcher.matches())
		return true;
		else
		return false;
	}
	
	/*******************************************************************************************************
	 - Function Name	:	viewRemoveOwnerDetails
	 - Input Parameters	:	String petOwnerId
	 - Return Type		:	PetBean
	 - Author			:	Sohan Madolli
	 - Creation Date	:	15/12/2018
	 - Description		:	calls dao method viewRemoveOwnerDetails(petOwnerId)
	 ********************************************************************************************************/

	@Override
	public PetBean viewRemoveOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		PetBean bean;
		bean=ipetdao.viewRemoveOwnerDetails(petOwnerId);
		return bean;
		
	}

	/*******************************************************************************************************
	 - Function Name	:	knowVaccinationDate()
	 - Input Parameters	:	
	 - Return Type		:	void
	 - Author			:	Sohan Madolli
	 - Creation Date	:	15/12/2018
	 - Description		:	calls dao method knowVaccinationDate()
	 ********************************************************************************************************/

	@Override
	public void knowVaccinationDate() throws PetException, ClassNotFoundException, IOException, SQLException
	{
	//	PetBean bean;
		ipetdao.knowVaccinationDate();
	//	return bean;
	
	}
	
}
