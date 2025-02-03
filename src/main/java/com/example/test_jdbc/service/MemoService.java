package com.example.test_jdbc.service;

import com.example.test_jdbc.dto.MemoRequestDto;
import com.example.test_jdbc.dto.MemoResponseDto;
import com.example.test_jdbc.entity.Memo;
import com.example.test_jdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtoList = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(),memo.getContent());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long memoId) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(() -> new IllegalArgumentException("해당 메모가 존재하지 않습니다."));

        return new MemoResponseDto(memo.getId(),memo.getContent());
    }

    @Transactional
    public MemoResponseDto updateContent(Long memoId, MemoRequestDto dto) {

        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        Memo updatedMemo = memoRepository.updateContent(memo.getId(), dto.getContent());

        return new MemoResponseDto(updatedMemo.getId(), updatedMemo.getContent());
    }

    @Transactional
    public void deleteById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        memoRepository.deleteById(memoId);
    }
}
