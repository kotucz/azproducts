package cz.kotu.demo.azproducts.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.kotu.demo.azproducts.loading.LoadingState
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.Flow

class ProductsViewModel @AssistedInject constructor(
    @Assisted categoryId: Long,
    productsRepository: ProductsRepository,
) : ViewModel() {

    val products: Flow<LoadingState<List<Product>>> = productsRepository.productsFlow(categoryId)

    @AssistedFactory
    interface Factory {
        fun create(categoryId: Long): ProductsViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            categoryId: Long,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(categoryId) as T
            }
        }
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AssistedInjectModule