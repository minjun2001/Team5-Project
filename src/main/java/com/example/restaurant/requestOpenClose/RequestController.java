package com.example.restaurant.requestOpenClose;

import com.example.restaurant.enumList.RequestStatus;
import com.example.restaurant.requestOpenClose.dto.CloseViewDto;
import com.example.restaurant.requestOpenClose.dto.OpenConfirmDto;
import com.example.restaurant.requestOpenClose.dto.OpenDto;
import com.example.restaurant.requestOpenClose.dto.OpenViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/user/openRequest")
    public ResponseEntity<?> openShop(
            @ModelAttribute OpenDto dto) {
        try {
            OpenViewDto openViewDto = requestService.openRestaurant(dto);
            return ResponseEntity.ok(openViewDto);
        }catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/admin/open/confirm/{openId}")
    public ResponseEntity<?> confirmRequest(
            @PathVariable Long openId,
            @RequestBody OpenConfirmDto dto
    ) {
        try {
            OpenViewDto openViewDto = requestService.openConfirm(openId,dto);
            return ResponseEntity.ok(openViewDto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/auth/opens/{openId}")
    public ResponseEntity<?> readOne(
            @PathVariable
            Long openId
    ) {
        try {
            OpenViewDto dto = requestService.readOne(openId);
            return ResponseEntity.ok(dto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/admin/opens/ReadAll")
    public ResponseEntity<?> adminReadAll(
            @RequestParam(required = false)
            RequestStatus status
    ) {
        try {
            List<OpenViewDto> dtoList = requestService.adminReadAll(status);
            return ResponseEntity.ok(dtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/auth/opens/readAll")
    public ResponseEntity<?> UserReadAll() {
        try {
            List<OpenViewDto> dtoList = requestService.userReadAll();
            return ResponseEntity.ok(dtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @PostMapping("rest/close")
    public ResponseEntity<?> closeRestaurant(
            @RequestParam
            String reason
    ) {
        try {
            CloseViewDto closeViewDto = requestService.closeRestaurant(reason);
            return ResponseEntity.ok(closeViewDto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @PutMapping("/admin/close/confirm/{closeId}")
    public ResponseEntity<?> closeConfirm(
            @PathVariable
            Long closeId
    ) {
        try {
            CloseViewDto closeViewDto = requestService.closeConfirm(closeId);
            return ResponseEntity.ok(closeViewDto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/auth/close/{closeId}")
    public ResponseEntity<?> readOneClose(
            @PathVariable
            Long closeId
    ) {
        try {
            CloseViewDto dto = requestService.readOneClose(closeId);
            return ResponseEntity.ok(dto);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/auth/close/readAll")
    public ResponseEntity<?> UserReadAllClose() {
        try {
            List<CloseViewDto> dtoList = requestService.userReadAllClose();
            return ResponseEntity.ok(dtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
    @GetMapping("/admin/close/ReadAll")
    public ResponseEntity<?> adminReadAllClose(
            @RequestParam(required = false)
            RequestStatus status
    ) {
        try {
            List<CloseViewDto> dtoList = requestService.adminReadAllClose(status);
            return ResponseEntity.ok(dtoList);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }




}
