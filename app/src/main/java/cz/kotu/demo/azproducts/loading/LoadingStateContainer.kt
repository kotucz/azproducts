package cz.kotu.demo.azproducts.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.kotu.demo.azproducts.ui.theme.ErrorRed

@Composable
inline fun <T> LoadingStateContainer(
    loadingState: LoadingState<T>,
    dataView: (T) -> Unit
) {
    when (loadingState) {
        is LoadingState.Loading -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        }
        is LoadingState.Data -> {
            dataView(loadingState.data)
        }
        is LoadingState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    color = ErrorRed,
                    text = loadingState.exception.toString()
                )
            }
        }
    }
}