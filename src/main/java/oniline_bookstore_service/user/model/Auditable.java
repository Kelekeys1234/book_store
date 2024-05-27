package oniline_bookstore_service.user.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Auditable {

	@Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    
    @Column(name = "updated_by")
    @LastModifiedBy
    private String updatedBy;
}