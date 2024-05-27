package oniline_bookstore_service.user.model;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "role")
public class Role extends Auditable implements Serializable {

	private static final long serialVersionUID = -5936600795228863823L;
	
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

	@ManyToMany
	@JoinTable(
			name = "roles_permissions",
			joinColumns = @JoinColumn(name = "roles_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private Set<Permission> permissions;

}
