package vn.trinhtung.service;

import vn.trinhtung.dto.RoleResponseDTO;

import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAll();
}
