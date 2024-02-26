package nl.nickwillems.koffiegrinder;

import org.springframework.stereotype.Service;


@Service
public class KoffieGrinder {
    boolean grindSlappeKoffie(final Boon boon) {
        System.out.println("%s bonen met sterkte %s gegrind voor slappe koffie".formatted(boon.getAmount(), boon.getStrength()));
        return true;
    }

    boolean grindMildeKoffie(final Boon boon) {
        System.out.println("%s bonen met sterkte %s gegrind voor milde koffie".formatted(boon.getAmount(), boon.getStrength()));
        return true;
    }

    boolean grindSterkeKoffie(final Boon boon) {
        System.out.println("%s bonen met sterkte %s gegrind voor sterke koffie".formatted(boon.getAmount(), boon.getStrength()));
        return true;
    }
}
