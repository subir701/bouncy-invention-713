package com.masai.service;




import com.masai.Exception.AdminException;

import com.masai.model.Admin;

public interface AdminService {

	Admin insertAdmin(Admin admin)throws AdminException;
	
	Admin deleteAdmin(Integer adminId)throws AdminException;
	
	
}
