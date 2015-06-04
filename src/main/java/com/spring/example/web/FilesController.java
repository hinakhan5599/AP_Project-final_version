
package com.spring.example.web;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.example.bean.Files;
import com.spring.example.service.FilesService;

public class FilesController extends MultiActionController {
    private FilesService filesService;

    public void setFilesService(FilesService filesService) {
        this.filesService = filesService;
    }

    /**
     * upload
     */
    
    public ModelAndView upload(HttpServletRequest request,
        HttpServletResponse response) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartRequest.getFile("file");

        Files file = new Files();
        file.setFilename(multipartFile.getOriginalFilename());
        file.setNotes(ServletRequestUtils.getStringParameter(request, "notes"));
        file.setType(multipartFile.getContentType());
        file.setFile(multipartFile.getBytes());

        this.filesService.save(file);

        return new ModelAndView("redirect:files.htm");
    }

    /**
     * download
     */

    public ModelAndView download(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        int id = ServletRequestUtils.getRequiredIntParameter(request, "id");

        Files file = this.filesService.find(id);

        response.setContentType(file.getType());
        response.setContentLength(file.getFile().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFilename() +"\"");
        
        FileCopyUtils.copy(file.getFile(), response.getOutputStream());

        return null;

    }

    /**
     * delete
     */
    
    public ModelAndView delete(HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        int id = ServletRequestUtils.getRequiredIntParameter(request, "id");

        this.filesService.delete(id);
        
        return new ModelAndView("redirect:files.htm");
    }

}
