package hyunbenny.springAdv.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderServiceV0;

    @GetMapping("/v0/save")
    public String save(String itemId) {
        orderServiceV0.orderItem(itemId);
        return itemId + " : save 성공";
    }
}
