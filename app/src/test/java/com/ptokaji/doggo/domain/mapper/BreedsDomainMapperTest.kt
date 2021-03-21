package com.ptokaji.doggo.domain.mapper

import org.junit.Assert.assertEquals
import org.junit.Test

class BreedsDomainMapperTest {

    private val sut = BreedsDomainMapper()

    @Test
    fun `GIVEN api data subBreeds is not empty WHEN fromApiToDomain THEN entity contains subBreeds`() {
        // GIVEN
        val mockBreed = "testBreed"
        val mockSubBreed = listOf("subBreed")
        val mockApi = mapOf(mockBreed to mockSubBreed)

        // WHEN
        val result = sut.fromApiToDomain(mockApi)

        // THEN
        assertEquals(mockBreed, result.first().name)
        assertEquals(mockSubBreed, result.first().subBreeds)
    }

    @Test
    fun `GIVEN api data is subBreeds empty WHEN fromApiToDomain THEN entity contains subBreeds`() {
        // GIVEN
        val mockBreed = "testBreed"
        val mockSubBreed = emptyList<String>()
        val mockApi = mapOf(mockBreed to mockSubBreed)

        // WHEN
        val result = sut.fromApiToDomain(mockApi)

        // THEN
        assertEquals(mockBreed, result.first().name)
        assert(result.first().subBreeds.isEmpty())
    }
}