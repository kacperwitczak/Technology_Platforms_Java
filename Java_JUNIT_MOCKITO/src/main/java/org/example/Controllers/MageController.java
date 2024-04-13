package org.example.Controllers;

import org.example.DTO.MageDTO.MageRequest;
import org.example.DTO.MageDTO.MageResponse;
import org.example.Entities.Mage;
import org.example.Repositories.MageRepository;

import java.util.Collection;
import java.util.Optional;

public class MageController {

    private final MageRepository mageRepository;
    public MageController(MageRepository mageRepository) {
        this.mageRepository = mageRepository;
    }

    public String find(String name) {
        Optional<MageResponse> mage = mageRepository.find(name);
        if (mage.isEmpty()) {
            return "not found";
        }
        return mage.get().toString();
    }
    public String delete(String name) {
        try {
            mageRepository.delete(name);
            return "done";
        } catch (IllegalArgumentException e) {
            return "not found";
        }
    }
    public String save(String name, String level) {
        try {
            MageRequest mageReq = new MageRequest(name, Integer.parseInt(level));
            mageRepository.save(mageReq);
            return "done";
        } catch (IllegalArgumentException e) {
            return "bad request";
        }
    }

}
