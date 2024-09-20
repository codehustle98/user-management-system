package org.codehustle.ums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "export_log")
@Data
public class ExportLog implements Serializable {

    @Id
    @GeneratedValue
    private Integer logId;

    private String filename;

    private String filePath;
}
