package org.SplitLedger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupSplitMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_split_id", nullable = false)
    private GroupSplit groupSplit;

    @Column(nullable = false)
    private BigDecimal amountOwed;

    @Column(nullable = false)
    private boolean paid;
}

