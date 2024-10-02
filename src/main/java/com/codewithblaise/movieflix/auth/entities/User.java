package com.codewithblaise.movieflix.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotBlank(message = "please enter username field")
    @Column(nullable = false,unique = true)
    private String username;
    @NotBlank(message = "please enter name field")
    private String name;
    @NotBlank(message = "please enter email field")
    @Email(message = "please enter a valid email")
    @Column(unique = true)
    private String email;
    @NotBlank(message = "please enter password field")
    @Size(min =5,message = "The password must have at least 5 characters")
    private String password;
    @OneToOne(mappedBy = "user")
   private RefreshToken refreshToken;
    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;
    @Enumerated(EnumType.STRING)
private UserRole role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
