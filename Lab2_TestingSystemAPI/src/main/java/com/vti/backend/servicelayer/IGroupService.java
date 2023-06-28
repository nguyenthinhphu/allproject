package com.vti.backend.servicelayer;

import java.util.List;

import com.vti.entity.MstGroup;
import com.vti.form.GroupFormForCreating;
import com.vti.form.GroupFormForUpdate;

public interface IGroupService {

	public List<MstGroup> getAllGroup();

	public MstGroup getGroupById(int idInput);

	public void createGroup(GroupFormForCreating form);

	public void updateGroupById(int id, GroupFormForUpdate form);

	public MstGroup getGroupByName(String nameGroup);
	
	public boolean isGroupExistsByName(String name);
}
