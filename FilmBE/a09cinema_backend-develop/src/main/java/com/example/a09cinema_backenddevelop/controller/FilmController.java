package com.example.a09cinema_backenddevelop.controller;
import com.example.a09cinema_backenddevelop.model.entity.Film;
import com.example.a09cinema_backenddevelop.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.a09cinema_backenddevelop.model.entity.Category;
import com.example.a09cinema_backenddevelop.model.entity.CategoryFilm;
import com.example.a09cinema_backenddevelop.service.*;
import com.example.a09cinema_backenddevelop.model.entity.SeatDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.a09cinema_backenddevelop.model.dto.SeatDetailDto;
import com.example.a09cinema_backenddevelop.model.dto.TimeDto;
import com.example.a09cinema_backenddevelop.service.SeatDetailService;
import com.example.a09cinema_backenddevelop.service.TimeService;
import java.time.LocalDate;
@RestController
@RequestMapping("/api/film")
@CrossOrigin("*")

public class FilmController {
    @Autowired
    private FilmService filmService;
    private LocalDate today = LocalDate.now();
    @Autowired
    private TimeService timeService;

    @Autowired
    private SeatDetailService seatDetailService;
    //    @Qualifier("filmServiceImpl")
    @GetMapping("/info")
    public Film getInfoFilm(@RequestParam("id") long id) {
        Film filmInfo = this.filmService.findFilmById(id);
        return filmInfo;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Film>> search(String value, Pageable pageable) {
        Page<Film> films = filmService.search(value, pageable);
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Film>> findFilmWithPage(Pageable pageable) {
        Page<Film> films = filmService.findAll(pageable);
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<Page<Film>> findSortWithPage(Pageable pageable) {
        Page<Film> films = filmService.findSort(pageable);
        if (films.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping(value = "getAllFilm")
    public ResponseEntity<List<Film>> getAllGenre() {
        return ResponseEntity.ok(filmService.findAllListFilm());
    }

    @GetMapping(value = "dateShowOfFilm/{id}")
    public ResponseEntity<List<SeatDetail>> getDateShowOfFilmById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(seatDetailService.getAllSeatDetailByIdFilm(id));
    }

    @GetMapping(value = "timeShowOfFilm/")
    public ResponseEntity<List<TimeDto>> getTimeShowOfFilmById(@RequestParam(value = "date_show") String date_show, @RequestParam(value = "film_id") String film_id) {

        List<TimeDto> timeDtos= timeService.findAllTimeShowBySeatDetail(date_show,film_id);
        System.out.println(timeDtos);
        return ResponseEntity.ok(timeService.findAllTimeShowBySeatDetail(date_show,film_id));
    }

    @GetMapping(value = "findFilmById/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(filmService.findFilmById(id));
    }

    @GetMapping(value = "getAllSeatDetailByIdSeat/{id}")
    public ResponseEntity<List<SeatDetail>> getAllSeatDetailByIdSeat(@PathVariable(value = "id") long id) {
        SeatDetail tmp = seatDetailService.getInfoSeatDetailById(id);
        System.out.println(tmp.getRoom().getId());
        System.out.println(seatDetailService.getAllSeatDetailByIdToChoose(tmp.getDateShow(),tmp.getFilm().getId(),tmp.getRoom().getId(),tmp.getTime().getId()));
        return new ResponseEntity<>(seatDetailService.getAllSeatDetailByIdToChoose(tmp.getDateShow(),tmp.getFilm().getId(),tmp.getRoom().getId(),tmp.getTime().getId()), HttpStatus.OK);
    }
    ///{name}/{dateShow}/{time_id}
//    @GetMapping(value = "findAllSeatDetailByCondition")
//    public ResponseEntity<SeatDetailDto> getFilmById(@RequestBody SeatDetailDto seatDetailDto) {
//        return ResponseEntity.ok(seatDetailService.getIdSeatDetailByBookingCondition(seatDetailDto.getName(),
//                seatDetailDto.getDate_show(), seatDetailDto.getTime_show_id()));
//    }

    @GetMapping(value = "findAllSeatDetailByCondition/")
    public ResponseEntity<SeatDetailDto> getFilmById(@RequestParam(value = "name") String name,
                                                     @RequestParam(value = "date_show") String date_show,
                                                     @RequestParam(value = "time_id") long time_id) {
        System.out.println(seatDetailService.getIdSeatDetailByBookingCondition(name,
                date_show, time_id));
        return ResponseEntity.ok(seatDetailService.getIdSeatDetailByBookingCondition(name,
                date_show, time_id));
    }

}
