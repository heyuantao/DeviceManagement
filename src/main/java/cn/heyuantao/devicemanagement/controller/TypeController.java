package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.dto.TypeRequestDTO;
import cn.heyuantao.devicemanagement.dto.TypeResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.TypeService;
import cn.heyuantao.devicemanagement.util.CustomItemPagination;
import cn.heyuantao.devicemanagement.util.QueryParamsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags={"设备类型管理接口"})
@RestController
@RequestMapping("/api/v1/type")
public class TypeController {
    @Resource
    TypeService typeService;

/*    @Autowired
    private HttpServletRequest request;*/

    @GetMapping
    public ResponseEntity<List<?>> list(//@RequestParam Map map,
                                        HttpServletRequest request,
                                        @RequestParam(value="name",defaultValue = "") String name,
                                        @RequestParam(value="description",defaultValue = "") String description,
                                        @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize){

        Map<String,Object> params = QueryParamsUtil.getRequestParamMapFromRequestServlet(request);
        PageHelper.startPage(pageNum,pageSize);
        List<Type> typeList = typeService.getTypesByParams(params);
        PageInfo<Type> pageInfo = new PageInfo<Type>(typeList);

        List<TypeResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {return new TypeResponseDTO(item);}).collect(Collectors.toList());
        CustomItemPagination customItemPagination = new CustomItemPagination(responseDTOs,pageInfo);
        return new ResponseEntity(customItemPagination,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated @RequestBody TypeRequestDTO typeRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Type addType = typeService.addType(typeRequestDTO.convertToDO());
        return new ResponseEntity(new TypeResponseDTO(addType),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrive(@PathVariable("id") Integer id){
        TypeResponseDTO responseDTO = new TypeResponseDTO(typeService.getTypeById(id));
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Integer id,
            @Validated @RequestBody TypeRequestDTO typeRequestDTO,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Type addType = typeService.updateTypeById(id,typeRequestDTO.convertToDO());
        TypeResponseDTO responseDTO = new TypeResponseDTO(addType);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        typeService.deleteById(id);
        return "";
    }
}
