package com.iadtec.hackathon.ExportFiles;

import com.iadtec.hackathon.DTO.ClienteResponseDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Component
public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");

        @SuppressWarnings("unchecked")
        List<ClienteResponseDTO> allRegisterOneResponseDTO = (List<ClienteResponseDTO>) model.get("allRegisterOne");

        document.add(new Paragraph("Generated Users " + LocalDate.now()));

        PdfPTable table = new PdfPTable(allRegisterOneResponseDTO.size());
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("Identificador", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nome", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("CPF", font));
        table.addCell(cell);

        allRegisterOneResponseDTO.forEach(registerOneResponseDTO -> {
            table.addCell(registerOneResponseDTO.getId().toString());
            table.addCell(registerOneResponseDTO.getNome() );
            table.addCell(registerOneResponseDTO.getCpf() );
        });

        document.add(table);
    }
}
