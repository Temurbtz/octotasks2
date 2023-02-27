package com.tasks.octotasks2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntityResponse {
    private String fileId;
    private String fileType;
    private String message;
    private boolean uploadStatus;
    private String downloadUri;
}
