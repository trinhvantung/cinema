package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.trinhtung.dto.RoleResponseDTO;
import vn.trinhtung.service.RoleService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/roles")
@RestController
public class RoleController {
    private final RoleService roleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<RoleResponseDTO> getAll() {
        return roleService.getAll();
    }
}
