package com.example.myradiofrance

import com.example.myradiofrance.domain.repository.BrandRepository
import com.example.myradiofrance.domain.usecase.GetBrandsUseCase
import com.example.myradiofrance.domain.util.Failure
import com.example.myradiofrance.domain.util.Resource
import com.example.myradiofrance.fixtures.BrandsFixtures.brandsResult
import com.example.myradiofrance.presentation.brands.BrandsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class BrandsViewModelTest {

    @Mock
    private lateinit var brandRepository: BrandRepository

    private lateinit var getBrandsUseCase: GetBrandsUseCase
    private lateinit var viewModel: BrandsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        getBrandsUseCase = GetBrandsUseCase(brandRepository)
    }

    @Test
    fun `verify state when brands are successfully fetched`() = runTest {
        `when`(getBrandsUseCase.execute()).thenReturn(Resource.Success(brandsResult))

        viewModel = BrandsViewModel(getBrandsUseCase)

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test brands state is correct
        assert(viewModel.state.value.brands == brandsResult)
    }

    @Test
    fun `verify state when fetching brands fails`() = runTest {
        `when`(getBrandsUseCase.execute())
            .thenReturn(Resource.Error(Failure.NetworkError("Some error")))

        viewModel = BrandsViewModel(getBrandsUseCase)

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test brands are null
        assert(viewModel.state.value.brands == null)
    }
}
