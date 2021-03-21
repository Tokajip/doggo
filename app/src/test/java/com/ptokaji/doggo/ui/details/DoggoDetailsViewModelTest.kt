package com.ptokaji.doggo.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ptokaji.doggo.domain.usecase.GetBreedUseCase
import com.ptokaji.doggo.domain.usecase.GetSubBreedUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class DoggoDetailsViewModelTest {

    private val getBreedUseCase: GetBreedUseCase = mockk(relaxed = true)
    private val getSubBreedUseCase: GetSubBreedUseCase = mockk(relaxed = true)

    private lateinit var sut: DoggoDetailsViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        sut = DoggoDetailsViewModel(getBreedUseCase, getSubBreedUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN subBreed is null WHEN getImages THEN getBreedUseCase is called`() {
        // GIVEN
        val mockBreed = "mockBreed"
        coEvery { getBreedUseCase.execute(mockBreed) } returns listOf("mockUrl")
        // WHEN
        sut.getImages(mockBreed, null).observeForTesting {
            // THEN
            coVerify { getBreedUseCase.execute(mockBreed) }
            coVerify(exactly = 0) { getSubBreedUseCase.execute(any(), any()) }
        }
    }

    @Test
    fun `GIVEN subBreed is not null WHEN getImages THEN getSubBreedUseCase is called`() {
        // GIVEN
        val mockBreed = "mockBreed"
        val mockSubBreed = "mockSubBreed"
        coEvery { getSubBreedUseCase.execute(mockBreed, mockSubBreed) } returns listOf("mockUrl")
        // WHEN
        sut.getImages(mockBreed, mockSubBreed).observeForTesting {
            // THEN
            coVerify(exactly = 0) { getBreedUseCase.execute(mockBreed) }
            coVerify { getSubBreedUseCase.execute(mockBreed, mockSubBreed) }
        }
    }
}

fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}