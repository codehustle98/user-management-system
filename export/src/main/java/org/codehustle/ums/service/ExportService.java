package org.codehustle.ums.service;

import org.codehustle.ums.entity.ExportLog;
import org.codehustle.ums.entity.User;
import org.codehustle.ums.repository.ExportLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
public class ExportService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExportLogRepository logRepository;

    public List<User> getUsers(){
        return userService.getUsers();
    }

    public void saveExportLog(File file){
        ExportLog log = new ExportLog();
        log.setFilePath(file.getPath());
        log.setFilename(file.getName());
        logRepository.save(log);
    }

    public Optional<ExportLog> getFilePath(Integer id){
        return logRepository.findById(id);
    }

    public List<ExportLog> getExportLogs(){
        return logRepository.findAll();
    }

    public void deleteFile(Integer id) throws IOException {
        Optional<ExportLog> fileLog = logRepository.findById(id);
        if (fileLog.isPresent()){
            if(Files.exists(Path.of(fileLog.get().getFilePath()))){
                Files.delete(Path.of(fileLog.get().getFilePath()));
                logRepository.deleteById(id);
            }
        }
    }
}
