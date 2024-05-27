package oniline_bookstore_service.Dao;

import oniline_bookstore_service.user.model.Role;

public interface RoleDao {
public Role saveRole(Role role);
public Role findByRoleName(String name);
}
