package com.medicine.donate.medicine.entity;

import com.medicine.donate.medicine.dto.enums.Gender;
import com.medicine.donate.medicine.dto.enums.UserActivationStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity
@Table(name = "[_user]")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID uuid; // Ensure that this is of type UUID

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(length = 50)
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserActivationStatus status;

    private String password;

    @PrePersist
    public void onPrePersist() {
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}
