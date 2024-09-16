package org.codehustle.ums.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehustle.ums.entity.User;
import org.codehustle.ums.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void uploadExcel(MultipartFile file) throws IOException {
        List<User> userList = new ArrayList<>();

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet xssfSheet = workbook.getSheetAt(0);

        int rows = xssfSheet.getPhysicalNumberOfRows();

        for (int i=0;i<rows;i++){
            if(i > 0){
                User user = new User();

                XSSFRow row = xssfSheet.getRow(i);

                user.setName(row.getCell(0).getStringCellValue());
                user.setEmail(row.getCell(1).getStringCellValue());
                user.setGender(row.getCell(2).getStringCellValue());
                user.setAge((int)row.getCell(3).getNumericCellValue());

                userList.add(user);
            }
        }

        if (!userList.isEmpty()){
            userService.saveAllUsers(userList);
        }

    }
}
