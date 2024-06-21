package com.example.mas_final_project.Controllers;

import com.example.mas_final_project.model.Client;
import com.example.mas_final_project.services.ClientService;
import org.springframework.stereotype.Component;

@Component
public class ClientController {

    private final ClientService cs;


    public ClientController(ClientService cs) {
        this.cs = cs;
    }

    public int giveRandomBonus(Long id)
    {
        int bonus = cs.giveRandomBonus(id);

        return bonus;
    }
}
