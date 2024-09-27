package com.example.haibazoshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStyleRequest {
    @NotBlank(message = "Style name can't be empty")
    @Size(min = 3, max = 255, message = "Style name must be between 3 and 255 characters")
    String name;
}