package com.cg.pa.dao;

public interface QueryMapper 
{
	 String Insert_Query="insert into pet_owner_details values(pet_seq.nextval,?,?,?,?,null)";
	 String Retrieve_all="select * from pet_owner_details order by owner_id";
	 String View_Details="select * from pet_owner_details where owner_Id=?";
	 String Delete_Details="delete from pet_owner_details where owner_Id=?";
	 String Update_vaccine_date="update pet_owner_details set NEXT_VACCINE_DATE =? where OWNER_ID=?";

}
