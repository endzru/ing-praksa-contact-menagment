package com.example.contactmenagment.controllers.controllersInterface;


import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponse(responseCode = "400", description = "Bad request!")
@ApiResponse(responseCode = "200", description = "OK!")
@ApiResponse(responseCode = "403", description = "Forbidden access!")
@ApiResponse(responseCode = "401", description = "Unauthorized access!")
public interface GlobalApiResponseSetup {
}