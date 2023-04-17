package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.config.JwtService;
import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.dto.ReservationDTO;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;
import be.technobel.emolibspring.model.form.reservation.CreateReservationForm;
import be.technobel.emolibspring.service.ReservationService;
import be.technobel.emolibspring.service.impl.BookServiceImpl;
import be.technobel.emolibspring.service.impl.ReservationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {

    private ReservationService reservationServiceImpl;
    private BookServiceImpl bookServiceImpl;

    //thao tac voi token
    private JwtService jwtService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl, BookServiceImpl bookServiceImpl, JwtService jwtService) {
        this.reservationServiceImpl = reservationServiceImpl;
        this.bookServiceImpl = bookServiceImpl;
        this.jwtService=jwtService;
    }

    public ReservationEntity toEntity(CreateReservationForm createReservationForm, Long idUser) {
        return new ReservationEntity(
                createReservationForm.getName(),
                createReservationForm.getAddress(),
                idUser,createReservationForm.getIdbook()
        );
    }

    public ReservationDTO toDTO(ReservationEntity reservationEntity) {
        return new ReservationDTO(
                reservationEntity.getId(),
                reservationEntity.getName(),
                reservationEntity.getAddress(),
                reservationEntity.getUserId(),
                this.bookServiceImpl.findById(reservationEntity.getBookId()).orElse(null),
                reservationEntity.getCreatedAt(),
                reservationEntity.getUpdatedAt()
        );
    }

    @GetMapping("")
    public ResponseEntity getReservations(@RequestHeader("Authorization") String token) {
        Long idUser= jwtService.getIdUser(token);
        List<ReservationEntity> reservationEntityList = this.reservationServiceImpl.getReservationByUserId(idUser);
        List<ReservationDTO> reservationDTOS = new ArrayList<ReservationDTO>();
        reservationEntityList.forEach(reservationEntity -> {
            ReservationDTO reservationDTO = toDTO(reservationEntity);
            reservationDTOS.add(reservationDTO);
        });
        return Response.setResponse(true, HttpStatus.OK, reservationDTOS);
    }

    @PostMapping("/create")
    public ResponseEntity createReservation(@RequestBody @Valid CreateReservationForm createReservationForm, @RequestHeader("Authorization") String token) {
        Long idUser= jwtService.getIdUser(token);
        Optional<BookEntity> bookEntity = this.bookServiceImpl.findById(createReservationForm.getIdbook());
        if (bookEntity.isEmpty()) {
            return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
        }

        ReservationEntity reservationEntity = toEntity(createReservationForm, idUser);

        ReservationEntity reservationInfo = this.reservationServiceImpl.create(reservationEntity);

        return Response.setResponse(true, HttpStatus.OK, toDTO(reservationEntity));
    }

//    @PutMapping("/renewal/{id}")
//    public ResponseEntity update(@PathVariable Long id) throws ParseException {
//        Optional<ReservationEntity> reservationInfo = this.reservationServiceImpl.findById(id);
//        if (reservationInfo.isPresent()) {
//            ReservationEntity reservation = reservationInfo.get();
//            String currentDate = String.valueOf(reservationInfo.orElse(null).getDeadline());
//            reservation.setDeadline(this.addMonthCurrentTime(currentDate).getTime());
//
//            ReservationEntity newReservationInfo = this.reservationServiceImpl.update(id, reservationInfo.get());
//            return Response.setResponse(true, HttpStatus.OK, newReservationInfo);
//        }
//        return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.reservationServiceImpl.delete(id);
        return Response.setResponse(true, HttpStatus.OK, null);
    }

//    private Calendar addMonthToDate(Integer month) {
//        Date currentDate = new Date();
//
//        // convert date to calendar
//        Calendar c = Calendar.getInstance();
//        c.setTime(currentDate);
//
//        c.add(Calendar.MONTH, month);
//        return c;
//    }
//
//    private Calendar addMonthCurrentTime(String currentDate) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(currentDate);
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.MONTH, 1);
//        return c;
//    }
}
