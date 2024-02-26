package nl.nickwillems.koffiegrinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;


class KoffieGrinderServiceTest {
    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer DEFAULT_GRIND_SIZE = 1;

    final KoffieGrinder koffieGrinderMock = Mockito.mock(KoffieGrinder.class);
    final KoffieGrinderService subject = new KoffieGrinderService(koffieGrinderMock);

    @BeforeEach
    void beforeEach() {
        Mockito.reset(koffieGrinderMock);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldGrindSlappeKoffie(final Integer inputStrength) {
        // Arrange
        Mockito.when(koffieGrinderMock.grindSlappeKoffie(Mockito.any())).thenReturn(true);
        final Boon input = new Boon(DEFAULT_AMOUNT, DEFAULT_GRIND_SIZE, inputStrength);

        // Act
        final boolean result = subject.grindKoffie(input);

        // Assert
        Assertions.assertTrue(result);

        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindSlappeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindMildeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSterkeKoffie(Mockito.any());

        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindSlappeKoffie(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void shouldGrindMildeKoffie(final Integer inputStrength) {
        // Arrange
        Mockito.when(koffieGrinderMock.grindMildeKoffie(Mockito.any())).thenReturn(true);
        final Boon input = new Boon(DEFAULT_AMOUNT, DEFAULT_GRIND_SIZE, inputStrength);

        // Act
        final boolean result = subject.grindKoffie(input);

        // Assert
        Assertions.assertTrue(result);

        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSlappeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindMildeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSterkeKoffie(Mockito.any());

        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindMildeKoffie(input);
    }

    @Test
    void shouldNotGrindSlappeKoffie_nullInput() {
        // Arrange
        final Boon input = null;

        // Act
        final Object result = Assertions.assertThrows(Exception.class, () -> subject.grindKoffie(input));

        // Assert
        Assertions.assertEquals(NullPointerException.class, result.getClass());
    }
}
