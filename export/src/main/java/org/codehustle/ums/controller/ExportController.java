package org.codehustle.ums.controller;

import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.codehustle.ums.entity.ExportLog;
import org.codehustle.ums.entity.User;
import org.codehustle.ums.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/export")
@CrossOrigin(origins = "*")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @GetMapping
    public ResponseEntity exportCsv() throws Exception {
        List<User> userList = exportService.getUsers();

        if(!Files.isDirectory(Path.of("/export"))){
            Files.createDirectory(Path.of("/export"));
        }
        String filename = "users_"+System.currentTimeMillis()+".csv";
        File file = new File("/export/"+filename);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        StreamingResponseBody stream = outputStream -> {
            try(Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)){
                try{
                    new StatefulBeanToCsvBuilder<User>(writer).build().write(userList);
                } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        stream.writeTo(fileOutputStream);
        exportService.saveExportLog(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<FileSystemResource> downloadExcel(@PathVariable("id")Integer id){
        if (Files.isDirectory(Path.of("/export"))){
            Optional<ExportLog> exportLog = exportService.getFilePath(id);
            FileSystemResource systemResource = new FileSystemResource(new File(exportLog.get().getFilePath()));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+exportLog.get().getFilename())
                    .body(systemResource);
        }
        return null;
    }

    @GetMapping(value = "/files")
    public ResponseEntity<List<ExportLog>> getAllExportLogs(){
        return ResponseEntity.ok(exportService.getExportLogs());
    }

    @DeleteMapping(value = "/file/{id}")
    public ResponseEntity deleteFile(@PathVariable Integer id) throws IOException {
        exportService.deleteFile(id);
        return ResponseEntity.ok().build();
    }
}
