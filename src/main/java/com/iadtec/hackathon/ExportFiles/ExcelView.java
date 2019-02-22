package com.iadtec.hackathon.ExportFiles;

import com.iadtec.hackathon.DTO.ClienteResponseDTO;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
public class ExcelView extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"report.xlsx\"");

        @SuppressWarnings("unchecked")
        List<ClienteResponseDTO> allRegisterOneResponseDTO = (List<ClienteResponseDTO>) model.get("allRegisterOne");

        Sheet sheet = workbook.createSheet("RegisterOne Detail");
        sheet.setDefaultColumnWidth(30);

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Identificador");
        header.getCell(0).setCellStyle(style);
        header.createCell(1).setCellValue("Nome");
        header.getCell(1).setCellStyle(style);
        header.createCell(2).setCellValue("Valor");


        int rowCount = 1;

        for(ClienteResponseDTO registerOneResponseDTO : allRegisterOneResponseDTO){
            Row userRow =  sheet.createRow(rowCount++);
            userRow.createCell(0).setCellValue(registerOneResponseDTO.getId());
            userRow.createCell(1).setCellValue(registerOneResponseDTO.getNome());
            userRow.createCell(2).setCellValue(registerOneResponseDTO.getCpf());
        }
    }
}
