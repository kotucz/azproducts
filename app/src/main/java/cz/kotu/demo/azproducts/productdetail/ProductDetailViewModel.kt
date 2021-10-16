package cz.kotu.demo.azproducts.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.kotu.demo.azproducts.loading.LoadingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.Flow

class ProductDetailViewModel @AssistedInject constructor(
    @Assisted val productId: Long,
    productDetailRepository: ProductDetailRepository,
) : ViewModel() {

    val product: Flow<LoadingState<ProductDetail>> = productDetailRepository.productFlow(productId)

    @AssistedFactory
    interface Factory {
        fun create(productId: Long): ProductDetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            productId: Long,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(productId) as T
            }
        }
    }
}
