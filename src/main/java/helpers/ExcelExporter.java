package helpers;

import models.Contact;
import models.ContactListModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {

    public  static void exportContactsToExcel(ContactListModel contactListModel, String fileName)throws IOException {
        List<Contact> contacts  = contactListModel.getContacts();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID", "Name", "Last Name", "Email", "Phone", "Address", "Description"};

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        for (int i = 0; i < headers.length; i++){
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        sheet.createFreezePane(0,1);

        int rowNum = 1;
        for (Contact contact : contacts){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(contact.getId());
            row.createCell(1).setCellValue(contact.getName());
            row.createCell(2).setCellValue(contact.getLastName());
            row.createCell(3).setCellValue(contact.getEmail());
            row.createCell(4).setCellValue(contact.getPhone());
            row.createCell(5).setCellValue(contact.getAddress());
            row.createCell(6).setCellValue(contact.getDescription());

        }
        for (int i = 0; i < headers.length; i++){
            sheet.autoSizeColumn(i);
        }
        try(FileOutputStream fileOut = new FileOutputStream(fileName)){
            workbook.write(fileOut);
        }
        workbook.close();

    }

}
