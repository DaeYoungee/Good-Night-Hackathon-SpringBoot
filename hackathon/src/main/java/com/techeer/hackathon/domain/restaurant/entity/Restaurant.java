package com.techeer.hackathon.domain.restaurant.entity;

import com.techeer.hackathon.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "RESTAURANT")
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Review 와 1:N 관계
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @Column(name = "restaurant_id", length = 30)
    private String restaurantName;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;

    @CreatedDate
    private LocalDateTime createdAt;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

    @Builder
    public Restaurant(
            String name,
            RestaurantCategory category) {
        this.restaurantName = name;
        this.category = category;
    }

    public void deleteEntity() {
        this.isDeleted = true;
    }

}
