package uk.co.harrymartland.playground;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class JacksonPlayground {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldIgnoreFields1To3() throws JsonProcessingException {
        ExampleClass exampleClass = new ExampleClass();
        Assert.assertEquals("{\"field4\":\"value4\"}", objectMapper.writeValueAsString(exampleClass));
    }

    private static class SuperClass {
        private String field1 = "value1";
        private String field2 = "value2";
        private String field3 = "value3";

        public String getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }

        public String getField3() {
            return field3;
        }
    }

    @JsonIgnoreProperties(value = {"field1", "field2"})
    private static class ExampleClass extends SuperClass {
        private String field4 = "value4";

        @Override
        @JsonIgnore
        public String getField3() {
            return super.getField3();
        }

        public String getField4() {
            return field4;
        }
    }
}
