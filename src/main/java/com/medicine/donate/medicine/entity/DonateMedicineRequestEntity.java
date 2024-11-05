package com.medicine.donate.medicine.entity;


import com.medicine.donate.medicine.dto.enums.DonationRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "donate_medicine_request")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DonateMedicineRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private DonationRequestStatus status;

    @Column(unique = true)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "owner_uuid", referencedColumnName = "uuid")
    private UserEntity user ;

    @ManyToOne
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private MedicineEntity medicine ;


    @OneToMany
    private List<DonationRequestImages> images;

    @OneToOne
    @JoinColumn(columnDefinition = "survey_id" , referencedColumnName = "id")
    private SurveyEntity survey;

    @PrePersist
    public void onPrePersist() {

        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now();
        }
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID();
        }
    }
}
