package com.vti.backend.servicelayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.backend.datalayer.IGroupRepository;
import com.vti.entity.MstGroup;
import com.vti.form.GroupFormForCreating;
import com.vti.form.GroupFormForUpdate;

@Service
public class GroupService implements IGroupService{

	@Autowired
	private IGroupRepository groupRepository;
	
	@Override
	public List<MstGroup> getAllGroup() {
		// TODO Auto-generated method stub
		return groupRepository.findAll();
	}

	@Override
	public MstGroup getGroupById(int idInput) {
		// TODO Auto-generated method stub
		return groupRepository.findById(idInput).get();
	}

	@Override
	public void createGroup(GroupFormForCreating form) {
		
		MstGroup group = new MstGroup();
		group.setGroupName(form.getName());
		groupRepository.save(group);
	}

	@Override
	public void updateGroupById(int id, GroupFormForUpdate form) {
		
		MstGroup group = getGroupById(id);
		group.setGroupName(form.getName());
		
		groupRepository.save(group);
	}

	@Override
	public MstGroup getGroupByName(String nameGroup) {
		// TODO Auto-generated method stub
		return groupRepository.findByGroupName(nameGroup);
	}

	@Override
	public boolean isGroupExistsByName(String name) {
		// TODO Auto-generated method stub
		return groupRepository.existsByGroupName(name);
	}

	
}
