package nl.nickwillems.koffiegrinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class KoffieGrinderServiceTestV2 {
    private KoffieGrinderService subject;
    private KoffieGrinder koffieGrinderMock;

    @BeforeEach
    void setUp() {
        koffieGrinderMock = Mockito.mock(KoffieGrinder.class);
        subject = new KoffieGrinderService(koffieGrinderMock);
    }

    @Test
    void shouldThrowNullPointerExceptionWhenBoonIsNull() {
        // arrange
        Executable method = () -> subject.grindKoffie(null);

        // act
        Throwable result = Assertions.assertThrows(NullPointerException.class, method);

        // assert
        Assertions.assertEquals(NullPointerException.class, result.getClass());
    }

    @ParameterizedTest
    @ValueSource(ints = {-99999, -10, -2, -1, 0})
    void shouldNotGrindKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);

        // act
        boolean result = subject.grindKoffie(boon);

        // assert
        Assertions.assertFalse(result);
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSlappeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindMildeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSterkeKoffie(Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldGrindSlappeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindSlappeKoffie(boon)).thenReturn(true);

        // act
        boolean result = subject.grindKoffie(boon);

        // assert
        Assertions.assertTrue(result);
        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindSlappeKoffie(boon);
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void shouldOnlyGrindSlappeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindSlappeKoffie(boon)).thenReturn(true);

        // act
        subject.grindKoffie(boon);

        // assert
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindMildeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSterkeKoffie(Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void shouldGrindMildeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindMildeKoffie(boon)).thenReturn(true);

        // act
        subject.grindKoffie(boon);

        // assert
        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindMildeKoffie(boon);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6})
    void shouldOnlyGrindMildeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindMildeKoffie(boon)).thenReturn(true);

        // act
        subject.grindKoffie(boon);

        // assert
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSlappeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSterkeKoffie(Mockito.any());
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 100, 99999})
    void shouldGrindSterkeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindSterkeKoffie(boon)).thenReturn(true);

        // act
        boolean result = subject.grindKoffie(boon);

        // assert
        Assertions.assertTrue(result);
        Mockito.verify(koffieGrinderMock, Mockito.times(1)).grindSterkeKoffie(boon);
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8, 100, 99999})
    void shouldOnlyGrindSterkeKoffie(int strength) {
        // arrange
        Boon boon = new Boon(0, 0, strength);
        Mockito.when(koffieGrinderMock.grindSterkeKoffie(boon)).thenReturn(true);

        // act
        subject.grindKoffie(boon);

        // assert
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindSlappeKoffie(Mockito.any());
        Mockito.verify(koffieGrinderMock, Mockito.never()).grindMildeKoffie(Mockito.any());
    }
}
