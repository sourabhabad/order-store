package com.planet.order;

import com.planet.order.service.impl.CsvFileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Collection;
import java.util.stream.Stream;

@SpringBootTest
class OrderServiceApplicationTests {

	@InjectMocks
	private CsvFileServiceImpl csvFileService;


	@ParameterizedTest
	@MethodSource("provideArguments")
	void testDetermineCountryWithValidData(String phoneNo, String expectedCountry) {
		System.out.println(csvFileService.classifyCountry(phoneNo));
		Assertions.assertEquals(expectedCountry, csvFileService.classifyCountry(phoneNo), "hello");
	}

	@ParameterizedTest
	@MethodSource("provideArgumentsInvalid")
	void testDetermineCountryWithInvalidData(String phoneNo, String expectedCountry) {
		System.out.println(csvFileService.classifyCountry(phoneNo));
		Assertions.assertNotEquals(expectedCountry, csvFileService.classifyCountry(phoneNo), "hello");
	}

	@Test
	void testDetermineCountryWithNullData() {
		Assertions.assertNotEquals("", csvFileService.classifyCountry("89786579846578"), "hello");
	}

	private static Stream<Arguments> provideArguments() {
		return Stream.of(
				Arguments.of("258803108183", "Mozambique"),
				Arguments.of("237 209993809", "Cameroon"),
				Arguments.of("256720708096", "Uganda"),
				Arguments.of("251252567912", "Ethiopia"),
				Arguments.of("212 808882110", "Morocco")
		);
	}

	private static Stream<Arguments> provideArgumentsInvalid() {
		return Stream.of(
				Arguments.of("258803108183", "India"),
				Arguments.of("237 209993809", "Nepal"),
				Arguments.of("256720708096", "South africa"),
				Arguments.of("251252567912", "Sudan")
		);
	}

}
