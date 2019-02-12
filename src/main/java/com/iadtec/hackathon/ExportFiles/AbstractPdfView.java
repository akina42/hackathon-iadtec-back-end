package com.iadtec.hackathon.ExportFiles;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public abstract class AbstractPdfView extends AbstractView {

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {

        // IE workaround: write into byte array first.
        ByteArrayOutputStream baos = createTemporaryOutputStream();

        Document document = new Document(PageSize.A4.rotate(), 36, 36, 54, 36);
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        prepareWriter(model, writer, httpServletRequest);
        buildPdfMetadata(model, document, httpServletRequest);

        document.open();
        buildPdfDocument(model, document, writer, httpServletRequest, httpServletResponse);
        document.close();

        // Flush to HTTP response.
        httpServletResponse.setHeader("Content-Disposition", "attachment");    // make browser to ask for download/display
        writeToResponse(httpServletResponse, baos);
    }

    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) throws DocumentException {
        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }


    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }


    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                             HttpServletRequest request, HttpServletResponse response) throws Exception;
}
