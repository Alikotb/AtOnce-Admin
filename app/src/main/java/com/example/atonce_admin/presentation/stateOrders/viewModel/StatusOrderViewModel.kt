import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atonce_admin.data.Response
import com.example.atonce_admin.domain.entity.WarehouseEntity
import com.example.atonce_admin.domain.usecase.GetOrdersByStatusUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class StatusOrderViewModel(
    private val getOrdersByStatusUseCase: GetOrdersByStatusUseCase
) : ViewModel() {

    private val _ordersState = MutableStateFlow<Response<List<WarehouseEntity>>>(Response.Loading)
    val ordersState = _ordersState.asStateFlow()

    private val currentItems = mutableListOf<WarehouseEntity>()

    private val pageSize = 10
    private var currentPage = 1
    var isLastPage = false
        private set
    private var isLoading = false

    fun loadNextPage(representativeId : Int, status : Int) {
        if (isLoading || isLastPage) return

        isLoading = true
        _ordersState.value = Response.Loading

        viewModelScope.launch {
            getOrdersByStatusUseCase(
                representativeId = representativeId,
                status = status,
                pageNumber = currentPage,
                pageSize = pageSize
            ).catch { e ->
                _ordersState.value = Response.Error(e.message ?: "Unknown error")
                isLoading = false
            }.collect { result ->
                isLastPage = currentPage >= result.totalPages
                currentPage++

                currentItems.addAll(result.items)
                _ordersState.value = Response.Success(currentItems)

                isLoading = false
            }
        }
    }
}
