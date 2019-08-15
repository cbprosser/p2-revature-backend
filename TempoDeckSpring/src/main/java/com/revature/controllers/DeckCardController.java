package com.revature.controllers;

import java.util.List;

import com.revature.models.Deck;
import com.revature.models.DeckCard;
import com.revature.services.DeckCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("deckCard")
public class DeckCardController {

    @Autowired
    private DeckCardService deckCardService;

     @GetMapping
     public List<DeckCard> findAll() {
         return deckCardService.findAll();
     }

    // @GetMapping("/decks/{deckId}")
    // public Deck findByDeckId(@RequestBody int deckId) {
    //     return deckCardService.findByDeckId(deckId);
    // }

    // Examples for Reference
    /**
     * @RestController @RequestMapping("brand") public class BrandController {
     * 
     * @Autowired private BrandService brandService;
     * 
     * @GetMapping public List<Brand> findAll(){ return brandService.findAll(); }
     * 
     *             @PostMapping("{brandId}/iceCream") 
     *              public ResponseEntity<Brand>
     *                  addIceCreamToBrand(@PathVariable int brandId, @RequestBody IceCream iceCream) { 
     *                  Brand updatedBrand = brandService.addIceCreamToBrand(brandId, iceCream);
     *                  ResponseEntity<Brand> resp = new ResponseEntity<Brand>(updatedBrand, HttpStatus.CREATED);
     *                   return resp; 
     *              }
     * @PutMapping public Brand update(@RequestBody Brand br) { return
     *             brandService.update(br); } }
     * 
     */

}