package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.data.repository.DoggoRepository
import com.ptokaji.doggo.domain.mapper.BreedsDomainMapper
import com.ptokaji.doggo.domain.model.BreedsEntity
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllBreedsUseCaseImplTest {

    private val mapper: BreedsDomainMapper = mockk(relaxed = true)
    private val repository: DoggoRepository = mockk(relaxed = true)

    private lateinit var sut: GetAllBreedsUseCaseImpl

    @Before
    fun setUp() {
        sut = GetAllBreedsUseCaseImpl(repository, mapper)
    }

    @Test
    fun `GIVEN repository returns success WHEN execute THEN mapper called`() = runBlocking {
        // GIVEN
        val mockBreedEntityList = listOf(mockk<BreedsEntity>(relaxed = true))
        val mockBreedsMap = mapOf("mockBreed" to emptyList<String>())
        coEvery { repository.getAllBreeds() } returns mockBreedsMap
        every { mapper.fromApiToDomain(mockBreedsMap) } returns mockBreedEntityList

        // WHEN
        val result = sut.execute()

        // THEN
        verify { mapper.fromApiToDomain(mockBreedsMap) }
        assertEquals(result, mockBreedEntityList)
    }

    @Test(expected = Exception::class)
    fun `GIVEN repository returns exception WHEN execute THEN throws exception and mapper not called`() =
        runBlocking {
            // GIVEN
            coEvery { repository.getAllBreeds() } throws Exception("mockMessage")

            // WHEN
            sut.execute()

            // THEN
            verify(exactly = 0) { mapper.fromApiToDomain(any()) }
        }
}