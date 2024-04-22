package com.pos.sale_dynamics.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Setter @Getter
public class ApplicationUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String avatarURL;
    private Boolean enabled = true;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;


    public ApplicationUser() {
        super();
        this.authorities = new HashSet<Role>();
    }

    public ApplicationUser(String username, String password, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public ApplicationUser(String username, String fullName, String password, Set<Role> authorities, String email, String phone) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }



    public ApplicationUser(Long id, String username, String password, Set<Role> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }
}
