package com.gdrg.contentcenter.controller.content;

import com.gdrg.contentcenter.domain.dto.share.ShareDTO;
import com.gdrg.contentcenter.service.content.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ShareService shareService;

    @GetMapping("/{id}")
    public ShareDTO findById(@PathVariable Integer id){
        return shareService.findById(id);
    }
}
