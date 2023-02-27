package com.tasks.octotasks2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="files")
public class FileEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(name="file_id")
    private String fileId;

    @Column(name="file_name")
    private String fileName;
    @Lob
    @Column(name="file_data")
    private byte[] fileData;
}
