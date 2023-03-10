package com.example.myradiofrance

import androidx.lifecycle.SavedStateHandle
import com.apollographql.apollo3.api.Optional
import com.example.myradiofrance.domain.repository.ShowsRepository
import com.example.myradiofrance.domain.usecase.GetShowsUseCase
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource
import com.example.myradiofrance.fixtures.ShowsFixtures
import com.example.myradiofrance.presentation.shows.ShowsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.eq

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ShowsViewModelTest {

    @Mock
    private lateinit var showsRepository: ShowsRepository

    private lateinit var getShowsUseCase: GetShowsUseCase
    private lateinit var viewModel: ShowsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        getShowsUseCase = GetShowsUseCase(showsRepository)
    }

    @Test
    fun `verify state when initial shows are successfully fetched`() = runTest {
        Mockito.`when`(getShowsUseCase.execute(any(), any(), any()))
            .thenReturn(Resource.Success(ShowsFixtures.showsResult))

        val savedStateHandle = SavedStateHandle().apply {
            set(ShowsViewModel.BRAND_ID_ARGUMENT, "FRANCEINTER")
        }

        viewModel = ShowsViewModel(getShowsUseCase, savedStateHandle)

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test shows state contains initial shows
        assert(viewModel.state.value.shows == ShowsFixtures.showsResult)
    }

    @Test
    fun `verify state when extra shows are successfully fetched`() = runTest {
        Mockito.`when`(getShowsUseCase.execute(any(), any(), any()))
            .thenReturn(Resource.Success(ShowsFixtures.showsResult))
        Mockito.`when`(getShowsUseCase.execute(any(), any(), eq(Optional.present("3"))))
            .thenReturn(Resource.Success(ShowsFixtures.extraShowsResult))

        val savedStateHandle = SavedStateHandle().apply {
            set(ShowsViewModel.BRAND_ID_ARGUMENT, "FRANCEINTER")
        }

        viewModel = ShowsViewModel(getShowsUseCase, savedStateHandle)
        viewModel.fetchShows(Optional.absent(), Optional.present("3"))

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test shows state contains merged shows
        assert(viewModel.state.value.shows == ShowsFixtures.mergedShowsResult)
    }

    @Test
    fun `verify state when initial fetch fails`() = runTest {
        Mockito.`when`(getShowsUseCase.execute(any(), any(), any()))
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))

        val savedStateHandle = SavedStateHandle().apply {
            set(ShowsViewModel.BRAND_ID_ARGUMENT, "FRANCEINTER")
        }

        viewModel = ShowsViewModel(getShowsUseCase, savedStateHandle)

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test shows are null
        assert(viewModel.state.value.shows == null)

        // Test error state is correct
        assert(viewModel.state.value.error == "Some error")
    }

    @Test
    fun `verify state when extra fetch fails`() = runTest {
        Mockito.`when`(getShowsUseCase.execute(any(), any(), any()))
            .thenReturn(Resource.Success(ShowsFixtures.showsResult))
        Mockito.`when`(getShowsUseCase.execute(any(), any(), eq(Optional.present("3"))))
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))

        val savedStateHandle = SavedStateHandle().apply {
            set(ShowsViewModel.BRAND_ID_ARGUMENT, "FRANCEINTER")
        }

        viewModel = ShowsViewModel(getShowsUseCase, savedStateHandle)
        viewModel.fetchShows(Optional.absent(), Optional.present("3"))

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test shows are null
        assert(viewModel.state.value.shows == null)

        // Test error state is correct
        assert(viewModel.state.value.error == "Some error")
    }
}