package com.tasks.octotasks2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileService implements IFileService{
  @Autowired
  private StudentRepository studentRepository;
    @Override
    public void populateFile(Path path) {
        List<Integer> range=readFromFile(path);
        List<Student> students=studentRepository.findAllById(range);
        writeToFile(path,students);
    }

    private List<Integer> readFromFile(Path path){
        ArrayList<Integer> range=new ArrayList<Integer>();
        try
        {
            FileInputStream file = new FileInputStream(new File(path.toString()));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator=sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    range.add((int)cell.getNumericCellValue());

                }
            }
            workbook.removeSheetAt(0);
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return range;
    }

    private void writeToFile(Path path, List<Student> students){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int numRow=0;
        int cellNum=0;
        for(Student student:students){
            Row row=sheet.createRow(numRow++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getFirstName());
            row.createCell(2).setCellValue(student.getLastName());
            row.createCell(3).setCellValue(student.getAddress());
        }
        try
        {
            FileOutputStream out = new FileOutputStream(new File(path.toString()));
            workbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
