package nl.nickwillems.koffiegrinder;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class Boon implements Serializable {
        @NotNull
        private final Integer amount;
        @NotNull
        private final Integer grindSize;
        @NotNull
        private final Integer strength;
}
