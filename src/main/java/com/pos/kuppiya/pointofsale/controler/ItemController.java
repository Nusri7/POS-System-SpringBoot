package com.pos.kuppiya.pointofsale.controler;

import com.pos.kuppiya.pointofsale.dto.ItemDTO;
import com.pos.kuppiya.pointofsale.dto.paginated.PaginatedResponseItemDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemNameBalQtyStateUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemPriceBalQtyUpdateDTO;
import com.pos.kuppiya.pointofsale.dto.request.ItemSaveRequestDTO;
import com.pos.kuppiya.pointofsale.service.ItemService;
import com.pos.kuppiya.pointofsale.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save-item")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String id = itemService.addItem(itemSaveRequestDTO);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Sucesssed", id), HttpStatus.CREATED
        );
    }

    @GetMapping(path = "/get-all-item")
    public ResponseEntity<StandardResponse> getItems() {
        List<ItemDTO> itemDTO = itemService.getItems();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemDTO), HttpStatus.OK
        );
    }

    @PutMapping(path = "/update-item-name-balQty", params = "id")
    public ResponseEntity<StandardResponse> updateItemPriceBalQty(@RequestParam(value = "id") int id, @RequestBody ItemPriceBalQtyUpdateDTO itemPriceBalQtyUpdateDTO) {
        String name = itemService.updateItemPriceBalQty(id, itemPriceBalQtyUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Selling price and Balance Quality updated Successfully!!", name)
                , HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-items-by-state/{state}")
    public ResponseEntity<StandardResponse> getItemsByState ( @PathVariable(value = "state") String state){
        if(state.equalsIgnoreCase("active") | state.equalsIgnoreCase("inactive")){
            boolean status = state.equalsIgnoreCase("active");

            List<ItemSaveRequestDTO> allItems = itemService.getItemsByStatus(status);

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "sort by states", allItems), HttpStatus.OK
            );

        }else {
            List<ItemDTO> itemDTO = itemService.getItems();

            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(200, "Success", itemDTO), HttpStatus.OK
            );

        }
    }
    @DeleteMapping(path = "/delete-item/{id}")
    public ResponseEntity<StandardResponse> deleteItem(@PathVariable(value = "id")int id){
        itemService.deleteItemById(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Item Deleted!!!",null),HttpStatus.OK
        );
    }
    @PutMapping(path = "/update-name-balQty-state/{id}")
    public ResponseEntity<StandardResponse> updateNameBalQtyState(@PathVariable(value = "id") int id, @RequestBody ItemNameBalQtyStateUpdateDTO itemNameBalQtyStateUpdateDTO){
        itemService.updateNameBalQtyState(id,itemNameBalQtyStateUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Updated Successfully!!!",null),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-count-of-active-item")
    public ResponseEntity<StandardResponse> getCountOfActiveItems(){
        int activeCount = itemService.getCountOfActiveItems();

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"active Items",activeCount),HttpStatus.OK
        );

    }
    @GetMapping(path = "/get-count-of-item-by-state",params = "state")
    public ResponseEntity<StandardResponse> getCountItemByState(@RequestParam(value = "state") String state){
        long itemCount = itemService.getCountItemByState(state);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"",itemCount),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-items",params = {"page","size"})
    public ResponseEntity<StandardResponse> getAllItems(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size") @Max(50) int size){

        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItems(page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"items by paging",paginatedResponseItemDTO),HttpStatus.OK
        );
    }
    @GetMapping(path = "/get-items-by-status-paginated", params = {"page","size","status"})

    public ResponseEntity<StandardResponse> getItemsByStatus(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "status") boolean status
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemsByState(page,size,status);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"items by status",paginatedResponseItemDTO),HttpStatus.OK
        );
    }

}
