package com.example.myradiofrance.presentation.brands

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myradiofrance.domain.model.Brand
import com.example.myradiofrance.domain.usecase.GetBrandsUseCase
import com.example.myradiofrance.domain.util.coFold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrandsViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BrandsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getBrandsUseCase.execute().coFold({ brands ->
                _state.update { it.copy(brands = brands, error = null) }
            }, { failure ->
                _state.update { it.copy(brands = null, error = failure.message) }
            })

            _state.update { it.copy(isLoading = false) }
        }
    }

    data class BrandsState(
        val isLoading: Boolean = false,
        val brands: List<Brand>? = null,
        val error: String? = null
    )
}
