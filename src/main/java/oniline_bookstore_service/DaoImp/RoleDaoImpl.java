package oniline_bookstore_service.DaoImp;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import oniline_bookstore_service.Dao.RoleDao;
import oniline_bookstore_service.repository.RoleRepository;
import oniline_bookstore_service.user.model.Role;


@Service
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findByRoleName(String name) {
		return roleRepository.findByName(name);
	}

}
