package pro.sky.wr_m.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.wr_m.service.SubscriptionService;
import pro.sky.wr_m.service.UzerService;
import pro.sky.wr_m.dto.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UzerController {

    private final UzerService userService;
    private final SubscriptionService subscriptionService;

    Logger logger = LoggerFactory.getLogger(UzerController.class);

    @Operation(summary = "Получить информацию о пользователе",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UzerDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<UzerDTO> getUser(@PathVariable Long id) {
        logger.debug("\"GET\" getUser method was invoke...");
        UzerDTO user = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @Operation(summary = "Создать нового пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UzerDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    )
            })
    @PostMapping()
    public ResponseEntity<UzerDTO> addUser(@Valid @RequestBody UzerDTO userDTO) {
        logger.debug("\"POST\" addUser method was invoke...");
        UzerDTO user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @Operation(summary = "Обновить пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UzerDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @PutMapping("/{id}")
    public ResponseEntity<UzerDTO> modifyUser(@Valid @RequestBody UzerDTO userDTO, @PathVariable Long id) {
        logger.debug("\"PUT\" modifyUser method was invoke...");
        UzerDTO user = userService.updateUser(userDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @Operation(summary = "Удалить пользователя",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UzerDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<UzerDTO> removeUser(@PathVariable Long id) {
        logger.debug("\"DELETE\" removeUser method was invoke...");
        UzerDTO user = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @Operation(summary = "Получить подписки пользователя",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(@PathVariable Long id) {
        logger.debug("\"GET\" getUserSubscriptions method was invoke...");
        List<SubscriptionDTO> subscriptions = subscriptionService.findUserSubscriptionsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(subscriptions);
    }

    @Operation(summary = "Добавить подписку",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Invalid Data"
                    )
            })
    @PostMapping("/{id}/subscriptions")
    public ResponseEntity<SubscriptionDTO> addUserSubscription(@Valid @RequestBody SubscriptionDTO subscriptionDTO
            , @PathVariable Long id) {
        logger.debug("\"POST\" addUserSubscription method was invoke...");
        SubscriptionDTO subscription = subscriptionService.addSubscription(subscriptionDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

    @Operation(summary = "Удалить подписку",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @DeleteMapping("/{id}/subscriptions/{sub_id}")
    public ResponseEntity<SubscriptionDTO> deleteUserSubscription(@PathVariable Long id
            , @PathVariable Long sub_id) {
        logger.debug("\"DELETE\" deleteUserSubscription method was invoke...");
        SubscriptionDTO subscription = subscriptionService.deleteSubscription(sub_id, id);
        return ResponseEntity.status(HttpStatus.OK).body(subscription);
    }

}
