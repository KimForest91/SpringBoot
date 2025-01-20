package com.codingapple.javatest.Sales;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.codingapple.javatest.member.Member;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ItemName;
    private Integer count;

    // 정확도가 필요없거나 지속적으로 변경되는 컬럼은 안빼도 됨
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "member_id", 
        foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;

    // 다른테이블에 있는 컬럼은 빼는게 나을 수도있음
    // String username;
    // String displayName;

    @CreationTimestamp
    private LocalDateTime created;
}
