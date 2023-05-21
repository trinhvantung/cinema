package vn.trinhtung.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.trinhtung.dto.RoleResponseDTO;
import vn.trinhtung.mapper.RoleMapper;
import vn.trinhtung.repository.RoleRepository;
import vn.trinhtung.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleResponseDTO> getAll() {

        return roleRepository.findAll().stream()
                .map(roleMapper::roleToRoleResponseDTO).collect(Collectors.toList());
    }
}
