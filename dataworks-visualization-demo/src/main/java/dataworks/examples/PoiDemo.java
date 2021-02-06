package dataworks.examples;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PoiDemo
{
    private static String getCellValue(XSSFCell cell)
    {
        if (cell == null)
            throw new NullPointerException();

        switch (cell.getCellType())
        {
            case NUMERIC:
                double doubleValue = cell.getNumericCellValue();
                return doubleValue + "";
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                boolean boolValue = cell.getBooleanCellValue();
                return Boolean.toString(boolValue);
            case BLANK:
                return "";
            case FORMULA:
                return cell.getCellFormula();
            case _NONE:
            case ERROR:
                return null;
        }

        return null;
    }

    public static void main(String[] args) throws IOException
    {
        String filePath = "testData\\mathematics\\geometry\\Rectangle\\IsRectangle_FalseCases.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));

        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++)
        {
            XSSFRow row = sheet.getRow(i);
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++)
            {
                XSSFCell cell = row.getCell(j);
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
