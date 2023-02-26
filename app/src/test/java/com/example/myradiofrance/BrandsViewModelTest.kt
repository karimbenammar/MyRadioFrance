package com.example.myradiofrance

import com.example.myradiofrance.domain.BrandClient
import com.example.myradiofrance.domain.GetBrandsUseCase
import com.example.myradiofrance.fixtures.BrandsFixtures.brandsResult
import com.example.myradiofrance.presentation.BrandsViewModel
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
    private lateinit var brandClient: BrandClient

    private lateinit var getBrandsUseCase: GetBrandsUseCase
    private lateinit var viewModel: BrandsViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setUp() {
        getBrandsUseCase = GetBrandsUseCase(brandClient)
    }

    @Test
    fun `verify state when brands are successfully fetched`() = runTest {
        `when`(getBrandsUseCase.execute()).thenReturn(brandsResult)

        viewModel = BrandsViewModel(getBrandsUseCase)

        // Test isLoading state is false
        assert(!viewModel.state.value.isLoading)

        // Test brands state is correct
        assert(viewModel.state.value.brands == brandsResult)
    }
}
