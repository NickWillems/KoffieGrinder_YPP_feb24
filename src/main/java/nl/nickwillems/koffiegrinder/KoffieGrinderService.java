package nl.nickwillems.koffiegrinder;

import org.springframework.stereotype.Service;

@Service
public class KoffieGrinderService {
    private final KoffieGrinder koffieGrinder;

    public KoffieGrinderService(final KoffieGrinder koffieGrinder) {
        this.koffieGrinder = koffieGrinder;
    }

    public boolean grindKoffie(final Boon boon) {
        if (boon.getStrength() <= 0) {
            return false;
        }

        if (boon.getStrength() <= 3) {
            return koffieGrinder.grindSlappeKoffie(boon);
        }

        if (boon.getStrength() <= 6) {
            return koffieGrinder.grindMildeKoffie(boon);
        }

        return koffieGrinder.grindSterkeKoffie(boon);
    }
}
