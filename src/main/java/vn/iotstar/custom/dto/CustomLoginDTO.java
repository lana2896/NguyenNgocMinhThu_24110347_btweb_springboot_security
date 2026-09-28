package vn.iotstar.custom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomLoginDTO {

	@NotBlank(message = "Username hoặc email không được để trống")
	private String login;

	@NotBlank(message = "Password không được để trống")
	private String password;

}
