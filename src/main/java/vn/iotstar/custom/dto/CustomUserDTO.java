package vn.iotstar.custom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDTO {

	private Long id;
	private String username;
	private String email;
	private String fullName;
	private String images;
	private String roleName;
	private boolean enabled;

}
