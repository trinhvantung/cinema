package vn.trinhtung.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.trinhtung.dto.ProfileRequestDTO;
import vn.trinhtung.dto.ProfileResponseDTO;
import vn.trinhtung.service.UserService;

@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ProfileResponseDTO getProfile(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getClaims().toString());
        return userService.getProfile(jwt.getClaimAsString("sub"));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequestDTO request,
                                           @AuthenticationPrincipal Jwt jwt) {
        userService.updateProfile(jwt.getClaimAsString("sub"), request);
        return ResponseEntity.ok().build();
    }
}
