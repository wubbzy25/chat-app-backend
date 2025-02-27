package com.chat.backend.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;

    public CustomUserDetails(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; // Puedes devolver true o false según tu lógica
    }

    @Override
    public String getPassword() {
        return null; // Devuelve null ya que no usas contraseña
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Devuelve null ya que no usas authorities
    }
}
