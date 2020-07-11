package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/owner/")
public class OwnerController {
    @Resource
    OwnerService ownerService;

    @GetMapping
    public List<Owner> list(){
        return ownerService.getOwners();
    }

    @PostMapping
    public Owner add(){
        Owner oneOwner = new Owner();
        Owner addOwner = ownerService.addOwner(oneOwner);
        return oneOwner;
    }

    @GetMapping("{id}")
    public Owner retrive(@PathVariable("id") Integer id){
        return ownerService.getOwnerById(id);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Integer id){
        ownerService.deleteById(id);
        return "";
    }
}
