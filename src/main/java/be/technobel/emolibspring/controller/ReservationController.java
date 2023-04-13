package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;
import be.technobel.emolibspring.model.form.reservation.CreateReservationForm;
import be.technobel.emolibspring.model.form.reservation.ResponseReservationForm;
import be.technobel.emolibspring.service.impl.BookServiceImpl;
import be.technobel.emolibspring.service.impl.ReservationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@CrossOrigin
public class ReservationController {

    private ReservationServiceImpl reservationServiceImpl;
    private BookServiceImpl bookServiceImpl;

    private UserServiceImpl userServiceImpl;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationServiceImpl, BookServiceImpl bookServiceImpl, UserServiceImpl userServiceImpl) {
        this.reservationServiceImpl = reservationServiceImpl;
        this.bookServiceImpl = bookServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity getReservations() {
        List<ReservationEntity> reservationEntityList = this.reservationServiceImpl.getReservationByUserId(1L);
        List<ResponseReservationForm> reservationResponseDTOs = new ArrayList<ResponseReservationForm>();
        reservationEntityList.forEach(reservationEntity -> {
            ResponseReservationForm responseReservationForm = new ResponseReservationForm(
                    reservationEntity.getId(),
                    reservationEntity.getName(),
                    reservationEntity.getAddress(),
                    reservationEntity.getDeadline(),
                    this.bookServiceImpl.findById(reservationEntity.getBookId()).orElse(null),
                    this.userServiceImpl.findById(reservationEntity.getUserId()).orElse(null)
            );
            reservationResponseDTOs.add(responseReservationForm);
        });
        return Response.setResponse(true, HttpStatus.OK, reservationResponseDTOs);
    }

    @PostMapping("/create/{slug}")
    public ResponseEntity createReservation(@RequestBody @Valid CreateReservationForm createReservationForm, @PathVariable String slug) {
        Optional<BookEntity> bookEntity = this.bookServiceImpl.getBookBySlug(slug);
        if (bookEntity.isEmpty()) {
            return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
        }

        ReservationEntity reservationEntity = new ReservationEntity(
                createReservationForm.getName(),
                createReservationForm.getAddress(),
                1L,
                addMonthToDate(1).getTime(),
                bookEntity.orElse(null).getId()
        );
        ReservationEntity reservationInfo = this.reservationServiceImpl.create(reservationEntity);

        return Response.setResponse(true, HttpStatus.OK, reservationInfo);
    }

    @PutMapping("/renewal/{id}")
    public ResponseEntity update(@PathVariable Long id) throws ParseException {
        Optional<ReservationEntity> reservationInfo = this.reservationServiceImpl.findById(id);
        if (reservationInfo.isPresent()) {
            ReservationEntity reservation = reservationInfo.get();
            String currentDate = String.valueOf(reservationInfo.orElse(null).getDeadline());
            reservation.setDeadline(this.addMonthCurrentTime(currentDate).getTime());

            ReservationEntity newReservationInfo = this.reservationServiceImpl.update(id, reservationInfo.get());
            return Response.setResponse(true, HttpStatus.OK, newReservationInfo);
        }
        return Response.setResponse(false, HttpStatus.NOT_FOUND, null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.reservationServiceImpl.delete(id);
        return Response.setResponse(true, HttpStatus.OK, null);
    }

    private Calendar addMonthToDate(Integer month) {
        Date currentDate = new Date();

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        c.add(Calendar.MONTH, month);
        return c;
    }

    private Calendar addMonthCurrentTime(String currentDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(currentDate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 1);
        return c;
    }
}
