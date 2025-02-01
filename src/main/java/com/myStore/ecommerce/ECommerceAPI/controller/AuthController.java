package com.myStore.ecommerce.ECommerceAPI.controller;

import com.myStore.ecommerce.ECommerceAPI.dto.AuthRequest;
import com.myStore.ecommerce.ECommerceAPI.dto.AuthResponse;
import com.myStore.ecommerce.ECommerceAPI.dto.UserDTO;

import com.myStore.ecommerce.ECommerceAPI.model.Login.User;
import com.myStore.ecommerce.ECommerceAPI.service.UserService;
import com.myStore.ecommerce.ECommerceAPI.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 📌 Endpoint para registrar un usuario
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        if (userService.existsByEmail(userDTO.getEmail())) { // ✅ Ahora este método sí existe
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El email ya está en uso");
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // 🔥 Encriptar la contraseña
        newUser.setRole(userDTO.getRole());

        userService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    /**
     * 📌 Endpoint para autenticar usuario y generar un token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 🔥 Obtener el usuario desde la base de datos para recuperar el userId
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 🔥 Obtener detalles de usuario desde Spring Security
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        // 🔥 Generar el token incluyendo el userId
        String token = jwtUtil.generateToken(userDetails, user.getId());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
