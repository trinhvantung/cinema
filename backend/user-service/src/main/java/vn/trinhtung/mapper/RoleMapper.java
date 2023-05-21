package vn.trinhtung.mapper;

import org.springframework.stereotype.Component;

import vn.trinhtung.dto.RoleRequestDTO;
import vn.trinhtung.dto.RoleResponseDTO;
import vn.trinhtung.entity.Role;

@Component
public class RoleMapper {
	public RoleResponseDTO roleToRoleResponseDTO(Role role) {
		return new RoleResponseDTO(role.getId(), role.getName());
	}

	public Role roleRequestDTOToRole(RoleRequestDTO request) {
		return new Role(request.getId(), null);
	}
}
