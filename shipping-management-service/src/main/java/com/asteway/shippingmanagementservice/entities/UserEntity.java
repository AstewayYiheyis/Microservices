package com.asteway.shippingmanagementservice.entities;

import com.asteway.shippingmanagementservice.enums.UserRoleEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    private String username;

    @Nullable
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @Nullable
    @ElementCollection(targetClass = UserRoleEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRoleEnum> roles;

    @Embedded
    private Address address;

    private String phoneNumber;
}
