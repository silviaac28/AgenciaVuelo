package com.agencia.reserva.application;

import java.util.List;

import com.agencia.reserva.domain.entity.Ciudad;
import com.agencia.reserva.domain.service.vueloService;

public class AsignarsillaUseCase {
    private final vueloService vueloService;

    public AsignarsillaUseCase(com.agencia.reserva.domain.service.vueloService vueloService) {
        this.vueloService = vueloService;
    }
  public  void execute()  {
                
    
            

        // vueloService.BuscarVuelo();
    }
}
