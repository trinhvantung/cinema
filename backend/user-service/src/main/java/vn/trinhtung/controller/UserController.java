package vn.trinhtung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.trinhtung.dto.*;
import vn.trinhtung.service.UserService;

@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('SCOPE_server')")
    @GetMapping(params = {"ids"})
    public List<UserResponseDTO> getAllUserByIds(@RequestParam List<Integer> ids) {
        return userService.getAllUserByIds(ids);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(params = {"page"})
    public Page<UserResponseDTO> getAllUserByPage(@RequestParam Integer page) {
        return userService.getAllUserByPage(page);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public UserResponseDTO getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ProfileResponseDTO getProfile(@AuthenticationPrincipal Jwt jwt) {
        return userService.getProfile(Integer.valueOf(jwt.getClaimAsString("id")));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequestDTO request,
                                           @AuthenticationPrincipal Jwt jwt) {
        userService.updateProfile(Integer.valueOf(jwt.getClaimAsString("id")), request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @RequestBody UserRequestDTO request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationRequestDTO request) {
        userService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registration/verification/{code}")
    public ResponseEntity<?> verifyRegister(@PathVariable String code) {
        userService.verifyRegister(code);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequestDTO request,
                                            @AuthenticationPrincipal Jwt jwt) {
        userService.updatePassword(Integer.valueOf(jwt.getClaimAsString("id")), request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email) {
        userService.resetPassword(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password/save-password")
    public ResponseEntity<?> savePassword(@RequestBody @Valid ResetPasswordRequestDTO request) {
        userService.savePassword(request);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/status/{userId}")
    public ResponseEntity<?> updateUserStatus(@RequestParam Integer status,
                                              @PathVariable Integer userId) {
        userService.updateUserStatus(userId, status);
        return ResponseEntity.ok().build();
    }
}
