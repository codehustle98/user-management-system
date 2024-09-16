package org.codehustle.ums.controller;

import org.codehustle.ums.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @PostMapping
    public void uploadExcel(@RequestParam("file")MultipartFile file) throws IOException {
        importService.uploadExcel(file);
    }
}
