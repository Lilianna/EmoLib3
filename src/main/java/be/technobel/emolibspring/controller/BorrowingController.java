package be.technobel.emolibspring.controller;

import be.technobel.emolibspring.config.JwtService;
import be.technobel.emolibspring.helper.Response;
import be.technobel.emolibspring.model.dto.BorrowingDTO;
import be.technobel.emolibspring.model.dto.ReservationDTO;
import be.technobel.emolibspring.model.entity.BookEntity;
import be.technobel.emolibspring.model.entity.BorrowingEntity;
import be.technobel.emolibspring.model.entity.ReservationEntity;
import be.technobel.emolibspring.model.form.reservation.BorrowingForm;
import be.technobel.emolibspring.model.form.reservation.CreateReservationForm;
import be.technobel.emolibspring.service.impl.BookServiceImpl;
import be.technobel.emolibspring.service.impl.BorrowingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrowing")
@RequiredArgsConstructor
public class BorrowingController {

    private BookServiceImpl bookService;
    private BorrowingServiceImpl borrowingService;
    private JwtService jwtService;

    public BorrowingController(BookServiceImpl bookService, BorrowingServiceImpl borrowingService, JwtService jwtServic) {
        this.borrowingService=borrowingService;
        this.bookService=bookService;
        this.jwtService=jwtService;
    }

    public BorrowingEntity toEntity(BorrowingForm borrowingForm, Long idUser) {

        return new BorrowingEntity(
                idUser,
                borrowingForm.getIssuer(),
                borrowingForm.getReceiver(),
                borrowingForm.getReturnDate(),
                borrowingForm.getFineAmount(),
                borrowingForm.getListBook()
        );
    }

    public BorrowingDTO toDTO(BorrowingEntity borrowingEntity) {
        List<BookEntity> listBook = new ArrayList<BookEntity>();
        List<Long> id = borrowingEntity.getListBook();
        for (Long x:
                id) {
            Optional<BookEntity> book = bookService.findById(x);
            if(book.isPresent()) {
                listBook.add(book.get());
            }
        }
        return new BorrowingDTO(
                borrowingEntity.getId(),
                borrowingEntity.getBorrowerId(),
                borrowingEntity.getIssuer(),
                borrowingEntity.getCreateDate(),
                borrowingEntity.getReceiver(),
                borrowingEntity.getReturnDate(),
                borrowingEntity.getFineAmount(),
                listBook
        );
    }

    //get all of user
    @GetMapping("")
    public ResponseEntity getAllBorrowing(@RequestHeader("Authorization") String token) {
        Long idUser = jwtService.getIdUser(token);

        List<BorrowingEntity> borrowingEntities = this.borrowingService.getByIdUser(idUser);
        List<BorrowingDTO> borrowingDTOS = new ArrayList<BorrowingDTO>();
        borrowingEntities.forEach(borrowingEntity -> {
            BorrowingDTO borrowingDTO = toDTO(borrowingEntity);
            borrowingDTOS.add(borrowingDTO);
        });
        return Response.setResponse(true, HttpStatus.OK, borrowingDTOS);
    }

    //them
    @PostMapping("/create")
    public ResponseEntity createReservation(@RequestBody @Valid BorrowingForm borrowingForm, @RequestHeader("Authorization") String token) {
        Long idUser= jwtService.getIdUser(token);

        BorrowingEntity borrowingEntity = toEntity(borrowingForm, idUser);

        BorrowingEntity reservationInfo = this.borrowingService.create(borrowingEntity);

        return Response.setResponse(true, HttpStatus.OK, toDTO(borrowingEntity));
    }

    //xoa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.borrowingService.delete(id);
        return Response.setResponse(true, HttpStatus.OK, null);
    }
}
