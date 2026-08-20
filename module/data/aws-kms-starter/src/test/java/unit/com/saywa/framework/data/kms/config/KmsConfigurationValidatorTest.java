package unit.com.saywa.framework.data.kms.config;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.kms.config.KmsConfiguration;
import com.saywa.framework.data.kms.config.KmsConfigurationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KmsConfigurationValidatorTest {

    @Mock
    private KmsConfiguration configuration;

    private KmsConfigurationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new KmsConfigurationValidator(configuration);
        lenient().when(configuration.defaultKeyRef()).thenReturn("");
        lenient().when(configuration.maxDirectPlaintextSize()).thenReturn(4096);
        lenient().when(configuration.maxMacMessageSize()).thenReturn(4096);
    }

    @Test
    void givenValidConfiguration_whenValidate_thenDoesNotThrow() {
        assertDoesNotThrow(() -> validator.validate());
    }

    @Test
    void givenNullDefaultKeyRef_whenValidate_thenThrowsIllegalArgumentException() {
        when(configuration.defaultKeyRef()).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenZeroMaxDirectPlaintextSize_whenValidate_thenThrowsIllegalArgumentException() {
        when(configuration.maxDirectPlaintextSize()).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenMaxDirectPlaintextSizeAboveAwsHardLimit_whenValidate_thenThrowsIllegalArgumentException() {
        when(configuration.maxDirectPlaintextSize()).thenReturn(5000);

        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }

    @Test
    void givenMaxMacMessageSizeAboveAwsHardLimit_whenValidate_thenThrowsIllegalArgumentException() {
        when(configuration.maxMacMessageSize()).thenReturn(5000);

        assertThrows(IllegalArgumentException.class, () -> validator.validate());
    }
}
