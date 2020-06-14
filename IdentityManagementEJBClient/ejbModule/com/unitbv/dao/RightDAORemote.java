package com.unitbv.dao;

import javax.ejb.Remote;

import com.unitbv.dto.RightDTO;

@Remote
public interface RightDAORemote extends GenericDAO<RightDTO> {

	RightDTO getRightByName(String rightName);

}
