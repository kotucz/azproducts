package cz.kotu.demo.azproducts.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ProductDetailViewModel @AssistedInject constructor(
    @Assisted val productId: Long,
) : ViewModel() {

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
