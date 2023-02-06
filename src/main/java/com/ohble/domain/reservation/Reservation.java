package com.ohble.domain.reservation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String placeAddress;

    @Column(nullable = false, length = 100)
    private String placeName;

    @Column(nullable = false)
    private LocalDateTime reservationTime;

    @Column(nullable = false, length = 100)
    private String clientName;

    @Column(nullable = false, length = 100)
    private String clientPhoneNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
