package com.glanner.api.controller;

import com.glanner.api.dto.request.SaveFreeBoardReqDto;
import com.glanner.api.dto.response.FindFreeBoardResDto;
import com.glanner.api.queryrepository.FreeBoardQueryRepository;
import com.glanner.api.service.BoardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/free-board")
public class FreeBoardController extends BoardController<SaveFreeBoardReqDto> {
    private final FreeBoardQueryRepository freeBoardQueryRepository;

    @Autowired
    public FreeBoardController(BoardService boardService, FreeBoardQueryRepository freeBoardQueryRepository) {
        super(boardService);
        this.freeBoardQueryRepository = freeBoardQueryRepository;
    }

    @GetMapping("/{page}/{limit}")
    public ResponseEntity<List<FindFreeBoardResDto>> getBoards(@PathVariable int page, @PathVariable int limit){
        List<FindFreeBoardResDto> responseDto =freeBoardQueryRepository.findPage(page, limit);
        return ResponseEntity.status(200).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindFreeBoardResDto> getBoard(@PathVariable Long id){
        FindFreeBoardResDto responseDto = freeBoardQueryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return ResponseEntity.status(200).body(responseDto);
    }

}
