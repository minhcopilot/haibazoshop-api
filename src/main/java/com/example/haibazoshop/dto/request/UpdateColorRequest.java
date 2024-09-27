package com.example.haibazoshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateColorRequest {
    @NotBlank(message = "Color name can't be empty")
            @Size(min = 2, max = 255, message = "Color name must be between 2 and 255 characters")
    String name;

    @NotBlank(message = "Hex code can't be empty")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Invalid hex code format")
    String hexCode;
}