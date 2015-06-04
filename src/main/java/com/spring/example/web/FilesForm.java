
package com.spring.example.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.example.service.FilesService;
import com.spring.example.bean.Files;

import java.util.List;

public class FilesForm extends AbstractController {
    private FilesService filesService;

    public void setFilesService(FilesService filesService) {
        this.filesService = filesService;
    }
    @RequestMapping(value="files.jsp")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
        HttpServletResponse response) throws Exception {

        List<Files> files = this.filesService.listAll();
        System.out.println("check");
        return new ModelAndView("files", "files", files);
    }
}
