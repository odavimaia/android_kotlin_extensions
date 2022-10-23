package com.davimaia.appextensions

/*** 
 * This function is used to make multiple lets with null safe
 * ***/
inline fun <T> onGuardLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) closure(elements.filterNotNull())
}
/*Example: */
// onGuardLet(file, url) { (file, url) ->
//        addImageCallback(file as File, url as String)
//   }

/*** 
 * This function helps to handle the collection of states in the view
 * ***/
fun coroutineViewState(
    state: ViewState,
    callbackError: ((String) -> Unit?)? = null,
    callbackEmpty: (() -> Unit)? = null,
    callbackLoading: (() -> Unit)? = null,
    callbackSuccess: ((Any?) -> Unit?)? = null
) {
    when (state) {
        is ViewState.Success<*> -> {
            callbackSuccess?.invoke(state.data)
        }
        is ViewState.Error -> {
            callbackError?.invoke(state.errorMsg.toString())
        }
        is ViewState.Empty -> {
            callbackEmpty?.invoke()
        }
        is ViewState.Loading -> {
            callbackLoading?.invoke()
        }
        else -> { /TODO: Nothing/ }
    }
}
/*Example: */
// private fun observeTutorialIsCompletedState() {
//     lifecycleScope.launch {
//         viewModel.tutorialIsCompletedState.flowWithLifecycle(
//             lifecycle, Lifecycle.State.CREATED
//         ).collect { state ->
//             coroutineViewState( 
//                 state,
//                 callbackSuccess = { handleTutorialIsCompletedSuccessState() },
//                 callbackError = ::handleTutorialIsCompletedErrorState,
//                 callbackLoading = ::handleLoadingState
//             )
//         }
//     }
// }