package org.SplitLedger.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.SplitLedger.entity.enums.Currency;
import org.SplitLedger.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.PreUpdate;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "lender_id", nullable = false)
    private User lender;

    @ManyToOne
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;

    private String description;


    @Column(name = "paid_at")
    private LocalDateTime paidAt;


    public void setStatus(Status status) {
        this.status = status;
        if (status == Status.PAID && this.paidAt == null) {
            this.paidAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void onUpdate() {
        if (this.status == Status.PAID && this.paidAt == null) {
            this.paidAt = LocalDateTime.now();
        }
    }


}
