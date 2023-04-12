package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.constants.StatusRoomEnum;
import be.technobel.emolibspring.constants.UserTypeEnum;
import be.technobel.emolibspring.helper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/master")
@RequiredArgsConstructor
public class MasterDataController {

    @GetMapping("/status")
    public ResponseEntity listStatusRoom() {
        return Response.setResponse(true, HttpStatus.OK, StatusRoomEnum.values());
    }

    @GetMapping("/user/type")
    public ResponseEntity getListUserType() {
        return Response.setResponse(true, HttpStatus.OK, UserTypeEnum.values());
    }


}
