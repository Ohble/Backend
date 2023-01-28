package com.ohble.ohble.oble.domain.survey;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String businessType;

    @Column(nullable = false, length = 50)
    private String detailBusinessType;

    @Column(nullable = false, length = 100)
    private String perceptionBlogMarketing;

    @Column(nullable = false, length = 100)
    private String counselingWay;

    @Column(nullable = false, length = 100)
    private String marketingWay;

    @Column(nullable = false, length = 10)
    private String targetGender;

    @Column(nullable = false, length = 10)
    private String targetAge;

    @Column(nullable = false, length = 100)
    private String targetType;

    @Column(nullable = false, length = 100)
    private String influencerType;

    @Column(nullable = false, length = 50)
    private String influencerVisitFrequency;

    @Column(nullable = false, length = 100)
    private String influencerAdvantage;

    @Column(nullable = false, length = 50)
    private String influencerVisitTime;

    @Column(nullable = false, length = 100)
    private String advertiserPlaceAddress;

    @Column(nullable = false, length = 30)
    private String advertiserName;

    @Column(nullable = false, length = 30)
    private String advertiserPhoneNumber;

    @Column(nullable = false, length = 100)
    private String bussinessStrength;

    @Column(nullable = false, length = 100)
    private String toAdmin;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
