package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.OwnerResponseDTO;
import cn.heyuantao.devicemanagement.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owner/")
public class OwnerController {
    @Resource
    OwnerService ownerService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<?>> list(){
        List<OwnerResponseDTO> responseDTOS= ownerService.getOwners().stream().map((item)-> {
            return new OwnerResponseDTO(item);
        }).collect(Collectors.toList());
        return new ResponseEntity(responseDTOS,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public Owner add(){
        Owner oneOwner = new Owner();
        Owner addOwner = ownerService.addOwner(oneOwner);
        return oneOwner;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> retrive(@PathVariable("id") Integer id){
        OwnerResponseDTO responseDTO = new OwnerResponseDTO(ownerService.getOwnerById(id));
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Integer id){
        ownerService.deleteById(id);
        return "";
    }
}
