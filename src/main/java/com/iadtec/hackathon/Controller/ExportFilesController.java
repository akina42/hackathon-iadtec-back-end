package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.Service.RegisterOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/api/v1/report")
@RestController
public class ExportFilesController {

    @Autowired
    private RegisterOneService registerOneService;

    public ExportFilesController() {
    }

    @GetMapping("/downloadPdf")
    public ModelAndView getDownloadFilePdf(Model model){
        return new ModelAndView("pdfView", "allRegisterOne", registerOneService.getAllRegisterOneExport());
    }

    @GetMapping("/downloadXlsx")
    public ModelAndView getDownloadFileXlsx(Model model){
        return new ModelAndView("excelView", "allRegisterOne", registerOneService.getAllRegisterOneExport());
    }
}
