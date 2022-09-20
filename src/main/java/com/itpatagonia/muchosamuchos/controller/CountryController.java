package com.itpatagonia.muchosamuchos.controller;

import com.itpatagonia.muchosamuchos.model.Country;
import com.itpatagonia.muchosamuchos.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    CountryService countryService;

    @Operation(summary = "Get a Countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Countries",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Country.class))
                    )
                    }),


            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        List<Country> countryList = (List<Country>)countryService.findAll();
        if(countryList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(countryList);
    }

    @Operation(summary = "Get a Country by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Country",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Country.class)
                            ,
                            examples = {
                                    @ExampleObject(name = "Example 1", summary = "Example 1", description = "algo1", value = "[{\"id\":\"13\",\"iso\":\"AR\",\"name\":\"Argentina\"}]"),
                                    @ExampleObject(name = "Example 2", summary = "Example 2", description = "algo2", value = "[{\"id\":\"14\",\"iso\":\"Ca\",\"name\":\"Canada\"}]")
                            }


                    ) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Country> getById(@Parameter(description = "id of Country to be searched") @PathVariable("id") Long id) {
        Country country = (Country)countryService.findById(id).get();
        if(country == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(country);
    }

    @PostMapping()
    public ResponseEntity<Country> save(@RequestBody Country country) {
        Country countryNew = (Country)countryService.create(Optional.of(country)).get();
        return ResponseEntity.ok(countryNew);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Country country) {
        Country countryUpdated = (Country)countryService.update(id, Optional.of(country)).get();
        return ResponseEntity.ok(countryUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if(countryService.delete(id)){
            return ResponseEntity.ok("Eliminado");
        }else{
            return ResponseEntity.status(400).body("Error al eliminar");
        }

    }
}
