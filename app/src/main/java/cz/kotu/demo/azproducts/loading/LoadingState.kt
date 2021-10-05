package cz.kotu.demo.azproducts.loading

sealed class LoadingState<out T> {
    class Loading : LoadingState<Nothing>()
    data class Data<T>(val data: T) : LoadingState<T>()
    data class Error(val exception: Exception) : LoadingState<Nothing>()
}