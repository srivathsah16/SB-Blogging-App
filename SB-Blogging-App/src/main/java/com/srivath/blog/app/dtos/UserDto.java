package com.srivath.blog.app.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    // Note: Giving message attribute in validation annotations is optional. If its
    // not provided, then defaultMessage from bindingResult of Exception will be
    // displayed.
    @NotEmpty
    private String name;

    // If you want custom message to be displayed to the user, you can provide in
    // message attribute and this message will be displayed.
    @Email(message = "should be a valid email id!!!")
    private String email;

    @NotEmpty
    @Length(min = 5, max = 15)
    private String password;

    @NotEmpty
    private String about;
}
