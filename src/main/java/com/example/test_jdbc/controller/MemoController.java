package com.example.test_jdbc.controller;

import com.example.test_jdbc.dto.MemoRequestDto;
import com.example.test_jdbc.dto.MemoResponseDto;
import com.example.test_jdbc.service.MemoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> saveMemo(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.saveMemo(dto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> findById(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.findById(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> updateContent(@PathVariable Long memoId, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.updateContent(memoId, dto));
    }

    @DeleteMapping("/memos/{memoId}")
    public void delete(@PathVariable Long memoId) {
        memoService.deleteById(memoId);
    }
}
