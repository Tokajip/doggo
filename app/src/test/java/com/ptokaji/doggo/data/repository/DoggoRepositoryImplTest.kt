package com.ptokaji.doggo.data.repository

import com.ptokaji.doggo.data.DoggoApi
import com.ptokaji.doggo.data.model.BreedsListResponse
import com.ptokaji.doggo.data.model.DogImagesResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DoggoRepositoryImplTest {

    private val api: DoggoApi = mockk(relaxed = true)
    private val ioDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var sut: DoggoRepositoryImpl


    @Before
    fun setUp() {
        sut = DoggoRepositoryImpl(api, ioDispatcher)
    }

    @Test
    fun `GIVEN api returns success WHEN getAllBreeds THEN api is called and result is not empty`() =
        runBlocking {
            // GIVEN
            val mockResponse = mockk<BreedsListResponse> {
                every { message } returns mapOf("mockBreed" to listOf("subBreed"))
            }
            coEvery { api.getBreedsList() } returns mockResponse
            // WHEN
            val result = sut.getAllBreeds()

            // THEN
            assertEquals(result, mockResponse.message)
        }

    @Test(expected = Exception::class)
    fun `GIVEN api returns error WHEN getAllBreeds THEN api is called and throws exception`(): Unit =
        runBlocking {
            // GIVEN
            coEvery { api.getBreedsList() } throws Exception("mockMessage")
            // WHEN
            sut.getAllBreeds()

        }

    @Test
    fun `GIVEN api returns success WHEN getBreed THEN api is called and result is not empty`() =
        runBlocking {
            // GIVEN
            val mockBreed = "mocnBreedName"
            val mockResponse = mockk<DogImagesResponse> {
                every { message } returns listOf("mockUrl")
            }
            coEvery { api.getBreed(mockBreed) } returns mockResponse
            // WHEN
            val result = sut.getBreed(mockBreed)

            // THEN
            coVerify { api.getBreed(mockBreed) }
            assertEquals(result, mockResponse.message)
        }

    @Test(expected = Exception::class)
    fun `GIVEN api returns error WHEN getBreed THEN api is called and throws exception`() =
        runBlocking {
            // GIVEN
            // GIVEN
            val mockBreed = "mocnBreedName"
            coEvery { api.getBreed(mockBreed) } throws Exception("mockMessage")
            // WHEN
            sut.getAllBreeds()

            // THEN
            coVerify { api.getBreed(mockBreed) }
        }


    @Test
    fun `GIVEN api returns success WHEN getSubBreed THEN api is called and result is not empty`() =
        runBlocking {
            // GIVEN
            val mockBreed = "mockBreedName"
            val mockSubBreed = "mockSubBreedName"
            val mockResponse = mockk<DogImagesResponse> {
                every { message } returns listOf("mockUrl")
            }
            coEvery { api.getSubBreed(mockBreed, mockSubBreed) } returns mockResponse
            // WHEN
            val result = sut.getSubBreed(mockBreed, mockSubBreed)

            // THEN
            coVerify { api.getSubBreed(mockBreed, mockSubBreed) }
            assertEquals(result, mockResponse.message)
        }

    @Test(expected = Exception::class)
    fun `GIVEN api returns error WHEN getSubBreed THEN api is called and throws exception`() =
        runBlocking {
            // GIVEN
            val mockBreed = "mockBreedName"
            val mockSubBreed = "mockSubBreedName"
            coEvery { api.getSubBreed(mockBreed, mockSubBreed) } throws Exception("mockMessage")
            // WHEN
            sut.getSubBreed(mockBreed, mockSubBreed)

            // THEN
            coVerify { api.getSubBreed(mockBreed, mockSubBreed) }
        }

}