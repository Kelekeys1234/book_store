package oniline_bookstore_service.user.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permission")
public class Permission extends Auditable implements Serializable {

	private static final long serialVersionUID = 1089210541763719798L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles ;

}
