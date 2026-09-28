package vn.iotstar.custom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
		name = "custom_users",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_custom_users_username", columnNames = "username"),
				@UniqueConstraint(name = "uk_custom_users_email", columnNames = "email")
		}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, unique = true, length = 150)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(name = "full_name", length = 150, columnDefinition = "nvarchar(200)")
	private String fullName;

	@Column(length = 500)
	private String images;

	@Column(nullable = false)
	@Builder.Default
	private boolean enabled = true;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private CustomRole role;

}
