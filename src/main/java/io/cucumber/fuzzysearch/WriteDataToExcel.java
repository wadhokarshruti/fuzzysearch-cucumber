package io.cucumber.fuzzysearch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteDataToExcel {
    private static final String FILE_NAME = "score-tracker.xlsx";
    public static final String ALGO = "AddressMatch";
    private final String[] columnHeaders = {"Dmographic Address List", "Address From Request", "FuzzyWuzzy Score",
            "FuzzyWuzzy Matched Value", "Ratcliff Obershelp Score", "Cosine Score", "String Equals"};

    public void writeOrUpdate(FuzzySearchModel model) throws IOException {
        if(fileExists()){
            update(model);
        }else{
            write(model);
        }
    }
    public void write(FuzzySearchModel model) throws IOException {
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        // Create a Sheet
        Sheet sheet = workbook.createSheet(ALGO);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create header cells
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            cell.setCellStyle(headerCellStyle);
        }
        // Create first entry after header in the sheet
        Row row = sheet.createRow(1);
        populateRow(model, row);
        resizeColumnsAndWriteInAFile(workbook, sheet);

    }

    public void update(FuzzySearchModel model) throws IOException {
        // Obtain a workbook from the excel file
        FileInputStream file = new FileInputStream(FILE_NAME);
        Workbook workbook = WorkbookFactory.create(file);

        if (Objects.nonNull(workbook)) {
            // Get Sheet at index 0
            Sheet sheet = workbook.getSheet(ALGO);
            int rowNum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.createRow(rowNum);
            populateRow(model, row);
            resizeColumnsAndWriteInAFile(workbook, sheet);
        }
    }

    public boolean fileExists() {
        File file = new File(FILE_NAME);
        System.out.println("Files exists? {}"+ file.exists());
        return file.exists();
    }

    private void resizeColumnsAndWriteInAFile(Workbook workbook, Sheet sheet) throws IOException {
        // Resize all columns to fit the content size
        for (int i = 0; i < columnHeaders.length; i++) {
            sheet.autoSizeColumn(i);
        }
        // Write the output to a file
        FileOutputStream fileOut = null;

        fileOut = new FileOutputStream(FILE_NAME);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }

    private void populateRow(FuzzySearchModel model, Row row) {
        String strList = model.getDemographicAddresses().toString();

        strList = strList.replace("[", "")
                .replace("]", "");

        System.out.println(strList);
        row.createCell(0).setCellValue(strList);
        row.createCell(1).setCellValue(model.getAddressFromRequest());
        row.createCell(2).setCellValue(model.getFuzzyWuzzyMatchedScore());
        row.createCell(3).setCellValue(model.getFuzzyWuzzyMatchedValue());
        row.createCell(4).setCellValue(model.getRatcliffObershelpSimilarity());
        row.createCell(5).setCellValue(model.getCosineSimilarity());
        row.createCell(6).setCellValue(model.isStringEquals());
    }
}
