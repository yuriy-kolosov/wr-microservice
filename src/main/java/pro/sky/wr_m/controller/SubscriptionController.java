package pro.sky.wr_m.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.wr_m.dto.SubscriptionTopDTO;
import pro.sky.wr_m.service.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Operation(summary = "Получить ТОП-3 популярных подписок",
            tags = "Подписки",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SubscriptionTopDTO.class)
                            )),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionTopDTO>> getTop3Subscriptions() {
        logger.debug("\"GET\" getTop3Subscriptions method was invoke...");
        List<SubscriptionTopDTO> top3Subscriptions = subscriptionService.findTop3Subscriptions();
        return ResponseEntity.status(HttpStatus.OK).body(top3Subscriptions);
    }

}
