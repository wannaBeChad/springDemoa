package validator;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailValidatorTest {
    @Test
    void emailTestValid() {
        boolean expected = true;
        String validEmail ="abc@ad.co";
        boolean actual = EmailValidator.validate(validEmail);
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void emailTestInvalid() {
        String validEmail ="abc@co";
        boolean actual = EmailValidator.validate(validEmail);
        assertThat(actual).isFalse();
    }
    @Test
    void emailTestInvalidWithTestDotCom() {
        String validEmail ="abc@Test.com";
        boolean actual = EmailValidator.validate(validEmail);
        assertThat(actual).isFalse();
    }



}
