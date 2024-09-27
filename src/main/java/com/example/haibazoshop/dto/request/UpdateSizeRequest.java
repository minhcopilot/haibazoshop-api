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
public class UpdateSizeRequest {
    @NotBlank(message = "Size name can't be empty")
            @Size(min = 1, max = 255, message = "Size name must be between 1 and 255 characters")
    String name;
}