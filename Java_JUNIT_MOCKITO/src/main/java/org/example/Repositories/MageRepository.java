package org.example.Repositories;

import org.example.DTO.MageDTO.MageRequest;
import org.example.DTO.MageDTO.MageResponse;
import org.example.Entities.Mage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private final Collection<Mage> mages = new ArrayList<>();

    public void delete(String name) {
        for (Mage m : mages) {
            if (m.getName().equals(name)) {
                mages.remove(m);
                return;
            }
        }
        throw new IllegalArgumentException("Mage not found");
    }

    public Optional<MageResponse> find(String name) {
        Optional<MageResponse> mage = Optional.empty();
        for (Mage m : mages) {
            if (m.getName().equals(name)) {
                mage = Optional.of(m.toMageResponse());
            }
        }
        return mage;
    }

    public void save(MageRequest mageReq) {
        Mage mage = mageReq.toMage();
        for (Mage m : mages) {
            if (m.getName().equals(mage.getName())) {
                throw new IllegalArgumentException("Mage already exists");
            }
        }
        mages.add(mage);
    }
}
